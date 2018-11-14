package com.si.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.board.dao.CommentDao;
import com.si.board.service.CommentService;
import com.si.board.vo.CommentVo;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;

	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public int writeComment(CommentVo vo) {
		return commentDao.writeComment(vo);
	}

	@Override
	public List<CommentVo> getCommentByPost(int postSeq, int page) {
		page = (page - 1) * 3;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postSeq", postSeq);
		map.put("page", page);

		return commentDao.getCommentByPost(map);
	}

	@Override
	public boolean isExistComment(int commentSeq) {
		return commentDao.isExistComment(commentSeq);
	}

	@Override
	public boolean isCorrectlyWriter(int commentSeq, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentSeq", commentSeq);
		map.put("userId", userId);

		return commentDao.isCorrectlyWriter(map);
	}

	@Override
	public int deleteComment(int commentSeq) {
		return commentDao.deleteComment(commentSeq);
	}
}