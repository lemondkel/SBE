package com.si.board.controller;

import com.si.board.service.BoardService;
import com.si.board.service.UserService;
import com.si.board.vo.BoardVo;
import com.si.board.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 회원에 관련된 컨트롤러
 *
 * @author l2jong
 * @since 2018-11-05
 */
@RequestMapping("/user")
@Controller
public class UserController {

	private final UserService userService;

	private final BoardService boardService;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	public UserController(UserService userService, BoardService boardService) {
		this.userService = userService;
		this.boardService = boardService;
	}

	/**
	 * 회원가입 페이지입니다.
	 *
	 * @return View
	 * @author l2jong
	 * @since 2018-11-05
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoinPage() {
		return "user/join";
	}

	/**
	 * 로그인을 처리합니다.
	 *
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-05
	 */
	@ResponseBody
	@RequestMapping(value = "/process/login", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public Map<String, Object> userLogin(@RequestBody UserVo userVo, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String encodedPassword = userService.getUserEncodedPassword(userVo.getUserId());
			if (userService.isExistUser(userVo.getUserId())) {
				// 아이디가 존재할 경우
				if (bCryptPasswordEncoder.matches(userVo.getUserPassword(), encodedPassword)) {
					// 비밀번호가 일치할 경우
					List<BoardVo> boardList = boardService.getAllBoard();

					session.setAttribute("login_user_id", userVo.getUserId());
					session.setAttribute("login_user_name", userService.getUserName(userVo.getUserId()));
					session.setAttribute("boardList", boardList);
					resultMap.put("desc", "로그인에 성공하였습니다.");
					resultMap.put("code", 200);
					resultMap.put("result", true);
				} else {
					// 비밀번호가 일치하지 않을 경우
					resultMap.put("desc", "비밀번호가 틀립니다.");
					resultMap.put("code", 901);
					resultMap.put("result", false);
				}
			} else {
				// 아이디가 존재하지 않을 경우
				resultMap.put("desc", "아이디가 존재하지 않습니다.");
				resultMap.put("code", 902);
				resultMap.put("result", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("desc", "서버 에러.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
		}

		return resultMap;
	}

	/**
	 * 로그아웃을 처리합니다.
	 *
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-05
	 */
	@ResponseBody
	@RequestMapping(value = "/process/logout", method = RequestMethod.POST)
	public Map<String, Object> userLogout(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			session.removeAttribute("login_user_id");
			session.removeAttribute("login_user_name");
			session.removeAttribute("boardList");
			resultMap.put("desc", "로그아웃에 성공하였습니다.");
			resultMap.put("code", 200);
			resultMap.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("desc", "서버 에러.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
		}
		return resultMap;
	}

	/**
	 * 회원가입 처리입니다.
	 *
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-06
	 */
	@ResponseBody
	@RequestMapping(value = "/process/join", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public Map<String, Object> userJoinPage(@RequestBody UserVo userVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userVo.setUserPassword(bCryptPasswordEncoder.encode(userVo.getUserPassword()));
			if (userService.insertUser(userVo) == 1) {
				resultMap.put("desc", "회원가입에 성공하였습니다.");
				resultMap.put("code", 200);
				resultMap.put("result", true);
			} else {
				resultMap.put("desc", "회원가입에 실패하였습니다.");
				resultMap.put("code", 600);
				resultMap.put("result", false);
			}
		} catch (NullPointerException e) {
			resultMap.put("desc", "빈 값을 채우고 시도해주세요.");
			resultMap.put("code", 901);
			resultMap.put("result", false);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("desc", "서버 에러.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
		}
		return resultMap;
	}
}