package com.si.board.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.si.board.service.UserService;

/**
 * 회원에 관련된 컨트롤러
 * 
 * @author l2jong
 * @since 2018-11-05
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	/**
	 * 회원가입 페이지입니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-05
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoinPage() {
		return "user/join";
	}

	/**
	 * 로그인을 처리합니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-05
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/process/login", method = RequestMethod.POST)
	public Map<String, Object> userLogin(@RequestParam("user_id") String userId,
			@RequestParam("user_password") String userPassword, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String encodedPassword = userService.getUserEncodedPassword(userId);
			if (userService.isExistUser(userId) == true) {
				// 아이디가 존재할 경우
				if (bCryptPasswordEncoder.matches(userPassword, encodedPassword) == true) {
					// 비밀번호가 일치할 경우
					session.setAttribute("login_user_id", userId);
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
	 * @author l2jong
	 * @since 2018-11-05
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/process/logout", method = RequestMethod.POST)
	public Map<String, Object> userLogout(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			session.removeAttribute("login_user_id");
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
	 * @author l2jong
	 * @since 2018-11-06
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/process/join", method = RequestMethod.POST)
	public Map<String, Object> userJoinPage(@RequestParam("user_id") String userId,
			@RequestParam("user_password") String userPassword, @RequestParam("user_name") String userName,
			@RequestParam("user_birth_day") String userBirthDay, @RequestParam("user_gender") char userGender,
			@RequestParam("user_contact") String userContact, @RequestParam("user_email") String userEmail,
			@RequestParam("user_zip_code") String userZipCode, @RequestParam("user_address1") String userAddress1,
			@RequestParam("user_address2") String userAddress2) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			map.put("userId", userId);
			map.put("userPassword", bCryptPasswordEncoder.encode(userPassword));
			map.put("userName", userName);
			map.put("userBirthDay", userBirthDay);
			map.put("userGender", userGender);
			map.put("userContact", userContact);
			map.put("userEmail", userEmail);
			map.put("userZipCode", userZipCode);
			map.put("userAddress1", userAddress1);
			map.put("userAddress2", userAddress2);

			if (userService.insertUser(map) == 1) {
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
