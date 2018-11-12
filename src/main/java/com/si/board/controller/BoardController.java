package com.si.board.controller;

import com.si.board.service.BoardService;
import com.si.board.service.CategoryService;
import com.si.board.service.PostService;
import com.si.board.vo.CategoryVo;
import com.si.board.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 게시판 컨트롤러입니다.
 *
 * @author l2jong
 * @since 2018-11-08
 */
@RequestMapping("/board")
@Controller
public class BoardController {

	private final BoardService boardService;

	private final PostService postService;

	private final CategoryService categoryService;

	@Autowired
	public BoardController(BoardService boardService, PostService postService, CategoryService categoryService) {
		this.boardService = boardService;
		this.postService = postService;
		this.categoryService = categoryService;
	}

	/**
	 * 게시판 페이지
	 *
	 * @param boardSeq    보드 시퀀스 번호
	 * @param categorySeq 카테고리 시퀀스 번호
	 * @param page        페이지
	 * @param model       모델
	 * @return ModelAndView
	 * @author l2jong
	 * @since 2018-11-08
	 */
	@RequestMapping(value = "/{boardSeq}/{categorySeq}/{page}", method = RequestMethod.GET)
	public String boardListPage(@PathVariable("boardSeq") int boardSeq, @PathVariable("categorySeq") int categorySeq,
								@PathVariable("page") int page, Model model) {
		if (!boardService.isExistBoard(boardSeq)) {
			// 없는 게시판일 경우
			return "redirect:/";
		}

		List<CategoryVo> categoryList = categoryService.getAllCategoryByBoard(boardSeq);
		List<PostVo> postList;

		if (categorySeq == 0) {
			// 전체일 경우
			postList = postService.getPostByBoard(boardSeq, page);
		} else {
			// 전체가 아닐 경우
			postList = postService.getPostByCategory(categorySeq, page);
		}

		if (page != 1 && postList == null) {
			// 페이지가 1페이지가 아니고, 게시물이 없을 경우
			return "redirect:/board/list/" + boardSeq + "/0/1";
		}

		model.addAttribute("page", page);
		model.addAttribute("boardSeq", boardSeq);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		return "board/list";
	}

	/**
	 * 게시물 작성 페이지입니다.
	 *
	 * @param boardSeq 보드 시퀀스 번호
	 * @param model    모델
	 * @return ModelAndView
	 * @author l2jong
	 * @since 2018-11-08
	 */
	@RequestMapping(value = "/write/{boardSeq}", method = RequestMethod.GET)
	public String boardWritePage(@PathVariable("boardSeq") int boardSeq, Model model, HttpSession session) {

		if (session.getAttribute("login_user_id") == null) {
			// 세션이 존재하지 않을 경우
			return "redirect:/";
		}

		if (!boardService.isExistBoard(boardSeq)) {
			// 없는 게시판일 경우
			return "redirect:/";
		}


		List<CategoryVo> categoryList = categoryService.getAllCategoryByBoard(boardSeq);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("boardSeq", boardSeq);
		return "board/write";
	}

	/**
	 * 게시물 조회 페이지입니다.
	 *
	 * @param postSeq 게시물 시퀀스 번호
	 * @param model   모델
	 * @return ModelAndView
	 * @author l2jong
	 * @since 2018-11-08
	 */
	@RequestMapping(value = "/view/{postSeq}", method = RequestMethod.GET)
	public String postViewPage(@PathVariable("postSeq") int postSeq, Model model, HttpSession session) {

		if (session.getAttribute("login_user_id") == null) {
			// 세션이 존재하지 않을 경우
			return "redirect:/";
		}

		if (!postService.isExistPost(postSeq)) {
			// 없는 게시판일 경우
			return "redirect:/";
		}
		PostVo postDetail = postService.getPostDetail(postSeq);

		model.addAttribute("postDetail", postDetail);
		model.addAttribute("postSeq", postSeq);
		return "board/view";
	}

	/**
	 * 게시물 작성 비즈니스 로직
	 *
	 * @param postVo  게시물 VO
	 * @param session 세션
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-08
	 */
	@ResponseBody
	@RequestMapping(value = "/process/write", method = RequestMethod.PUT)
	public Map<String, Object> writePostProcess(@RequestBody PostVo postVo, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String postRegUserId;
		try {
			postRegUserId = session.getAttribute("login_user_id").toString();
		} catch (Exception e) {
			resultMap.put("desc", "로그인이 필요합니다.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
			return resultMap;
		}
		postVo.setPostRegUserId(postRegUserId);

		try {
			if (postService.insertPost(postVo) == 1) {
				resultMap.put("desc", "게시물 작성에 성공하였습니다.");
				resultMap.put("code", 200);
				resultMap.put("result", true);
			} else {
				resultMap.put("desc", "게시물 작성에 실패하였습니다.");
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

	/**
	 * 게시물 삭제 비즈니스 로직
	 *
	 * @param postSeq 게시물 번호
	 * @param session 세션
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-11
	 */
	@ResponseBody
	@RequestMapping(value = "/process/delete", method = RequestMethod.DELETE)
	public Map<String, Object> deletePostProcess(@RequestParam("post_seq") int postSeq, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String loginUserId;
		try {
			loginUserId = session.getAttribute("login_user_id").toString();
		} catch (Exception e) {
			resultMap.put("desc", "로그인이 필요합니다.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
			return resultMap;
		}

		try {
			// 세션에 있는 사용자가 해당 게시물을 남긴 사용자인지 검증합니다.
			if (postService.isCorrectlyWriter(postSeq, loginUserId)) {
				// 일치할 경우
				if (postService.deletePost(postSeq) == 1) {
					resultMap.put("desc", "게시물 삭제에 성공하였습니다.");
					resultMap.put("code", 200);
					resultMap.put("result", true);
				} else {
					resultMap.put("desc", "게시물 삭제에 실패하였습니다.");
					resultMap.put("code", 600);
					resultMap.put("result", false);
				}
			} else {
				// 게시물을 남긴 사용자와 세션에 남아 있는 사용자와 다를 경우
				resultMap.put("desc", "올바른 사용자가 아닙니다.");
				resultMap.put("code", 700);
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
}