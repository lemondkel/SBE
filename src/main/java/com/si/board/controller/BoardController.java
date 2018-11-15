package com.si.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * @param boardSeq
	 *            보드 시퀀스 번호
	 * @param categorySeq
	 *            카테고리 시퀀스 번호
	 * @param page
	 *            페이지
	 * @param model
	 *            모델
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
		int postSum;

		if (categorySeq == 0) {
			// 전체일 경우
			postList = postService.getPostByBoard(boardSeq, page);
			postSum = postService.getPostByBoardSum(boardSeq);
		} else {
			// 전체가 아닐 경우
			postList = postService.getPostByCategory(categorySeq, page);
			postSum = postService.getPostByCategorySum(categorySeq);
		}

		if (page != 1 && postList == null) {
			// 페이지가 1페이지가 아니고, 게시물이 없을 경우
			return "redirect:/board/list/" + boardSeq + "/0/1";
		}
		
		String boardName = boardService.getBoardName(boardSeq);

		model.addAttribute("boardName", boardName);
		model.addAttribute("page", page);
		model.addAttribute("boardSeq", boardSeq);
		model.addAttribute("categorySeq", categorySeq);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("postSum", postSum);
		return "board/list";
	}

	/**
	 * 게시물 작성 페이지입니다.
	 *
	 * @param boardSeq
	 *            보드 시퀀스 번호
	 * @param model
	 *            모델
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
}