package com.si.board.service;

import java.util.List;

import com.si.board.vo.CommentVo;

public interface CommentService {
	int writeComment(CommentVo commentVo);
	
	List<CommentVo> getCommentByPost(int postSeq, int page);
}