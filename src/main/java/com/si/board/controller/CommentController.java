package com.si.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.si.board.service.CommentService;
import com.si.board.vo.CommentVo;

/**
 * 댓글 컨트롤러입니다.
 *
 * @author l2jong
 * @since 2018-11-08
 */
@RequestMapping("/comment")
@Controller
public class CommentController {

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * 댓글 작성 비즈니스 로직
	 *
	 * @param commentVo
	 *            댓글 VO
	 * @param session
	 *            세션
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-14
	 */
	@ResponseBody
	@RequestMapping(value = "/process/write", method = RequestMethod.PUT)
	public Map<String, Object> writePostProcess(@RequestBody CommentVo commentVo, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String commentRegUserId;
		try {
			commentRegUserId = session.getAttribute("login_user_id").toString();
		} catch (Exception e) {
			resultMap.put("desc", "로그인이 필요합니다.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
			return resultMap;
		}
		commentVo.setCommentRegUsrId(commentRegUserId);

		try {
			if (commentService.writeComment(commentVo) == 1) {
				resultMap.put("desc", "댓글 작성에 성공하였습니다.");
				resultMap.put("code", 200);
				resultMap.put("result", true);
				resultMap.put("username", session.getAttribute("login_user_name").toString());
				resultMap.put("commentSeq", commentVo.getCommentSeq());
			} else {
				resultMap.put("desc", "댓글 작성에 실패하였습니다.");
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
	 * 댓글을 가져옵니다.
	 *
	 * @param session
	 *            세션
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-14
	 */
	@ResponseBody
	@RequestMapping(value = "/process/view_more/{postSeq}/{page}", method = RequestMethod.GET)
	public Map<String, Object> viewMoreProcess(@PathVariable("postSeq") int postSeq, @PathVariable("page") int page,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (session.getAttribute("login_user_id") == null) {
			resultMap.put("desc", "로그인이 필요합니다.");
			resultMap.put("code", 900);
			resultMap.put("result", false);
			return resultMap;
		}

		try {
			List<CommentVo> commentList = commentService.getCommentByPost(postSeq, page);

			if (commentList.size() != 0) {
				resultMap.put("desc", "댓글을 정상적으로 가져왔습니다.");
				resultMap.put("code", 200);
				resultMap.put("result", true);
				resultMap.put("commentList", commentList);
				resultMap.put("loginUserId", session.getAttribute("login_user_id").toString());
			} else {
				resultMap.put("desc", "더이상 댓글이 존재하지 않습니다.");
				resultMap.put("code", 901);
				resultMap.put("result", false);
			}
		} catch (NullPointerException e) {
			resultMap.put("desc", "더이상 댓글이 존재하지 않습니다.");
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
	 * 댓글 삭제 비즈니스 로직
	 *
	 * @param commentSeq
	 *            댓글 번호
	 * @param session
	 *            세션
	 * @return Map
	 * @author l2jong
	 * @since 2018-11-14
	 */
	@ResponseBody
	@RequestMapping(value = "/process/delete/{comment_seq}", method = RequestMethod.DELETE)
	public Map<String, Object> deletePostProcess(@PathVariable("comment_seq") int commentSeq, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String loginUserId;

		try {
			// 게시물이 존재하는지 확인합니다.
			if (commentService.isExistComment(commentSeq)) {
				try {
					// 세션에 아이디가 존재하는지 확인합니다.
					loginUserId = session.getAttribute("login_user_id").toString();
				} catch (Exception e) {
					resultMap.put("desc", "로그인이 필요합니다.");
					resultMap.put("code", 900);
					resultMap.put("result", false);
					return resultMap;
				}

				// 세션에 있는 사용자가 해당 게시물을 남긴 사용자와 같은지 비교합니다.
				if (commentService.isCorrectlyWriter(commentSeq, loginUserId)) {
					// 일치할 경우
					if (commentService.deleteComment(commentSeq) == 1) {
						resultMap.put("desc", "댓글 삭제에 성공하였습니다.");
						resultMap.put("code", 200);
						resultMap.put("result", true);
					} else {
						// 삭제가 정상적으로 이루어지지 않았을 경우
						resultMap.put("desc", "댓글 삭제에 실패하였습니다.");
						resultMap.put("code", 600);
						resultMap.put("result", false);
					}
				} else {
					// 게시물을 남긴 사용자와 세션에 남아 있는 사용자와 다를 경우
					resultMap.put("desc", "올바른 사용자가 아닙니다.");
					resultMap.put("code", 700);
					resultMap.put("result", false);
				}
			} else {
				// 게시물이 존재하지 않을 경우
				resultMap.put("desc", "댓글이 존재하지 않습니다.");
				resultMap.put("code", 800);
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