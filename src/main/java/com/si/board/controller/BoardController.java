package com.si.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.si.board.service.BoardService;
import com.si.board.service.CategoryService;
import com.si.board.service.PostService;
import com.si.board.vo.CategoryVo;
import com.si.board.vo.PostVo;

/**
 * 게시판 컨트롤러입니다.
 * 
 * @author l2jong
 * @since 2018-11-08
 *
 */
@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 게시판 페이지
	 * 
	 * @author l2jong
	 * @since 2018-11-08
	 * @return
	 */
	@RequestMapping(value = "/{boardSeq}/{categorySeq}/{page}", method = RequestMethod.GET)
	public String boardListPage(@PathVariable("boardSeq") int boardSeq, @PathVariable("categorySeq") int categorySeq,
			@PathVariable("page") int page, Model model) {
		if (boardService.isExistBoard(boardSeq) == false) {
			// 없는 게시판일 경우
			return "redirect:/";
		}

		List<CategoryVo> categoryList = categoryService.getAllCategoryByBoard(boardSeq);
		List<PostVo> postList = null;

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
	 * @author l2jong
	 * @since 2018-11-08
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/write/{boardSeq}", method = RequestMethod.GET)
	public String boardWritePage(@PathVariable("boardSeq") int boardSeq, Model model) {
		if (boardService.isExistBoard(boardSeq) == false) {
			// 없는 게시판일 경우
			return "redirect:/";
		}

		List<CategoryVo> categoryList = categoryService.getAllCategoryByBoard(boardSeq);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("boardSeq", boardSeq);
		return "board/write";
	}

	/**
	 * 게시물 작성 비즈니스 로직
	 * 
	 * @author l2jong
	 * @since 2018-11-08
	 * @param postVo
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/process/write", method = RequestMethod.PUT)
	public Map<String, Object> writePostProcess(@RequestBody PostVo postVo, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String postRegUserId = session.getAttribute("login_user_id").toString();
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
}