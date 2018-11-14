package com.si.board.service;

import java.util.List;

import com.si.board.vo.CommentVo;

public interface CommentService {
	int writeComment(CommentVo commentVo);
	
	List<CommentVo> getCommentByPost(int postSeq, int page);

	boolean isExistComment(int commentSeq);

	boolean isCorrectlyWriter(int commentSeq, String loginUserId);

	int deleteComment(int commentSeq);
}