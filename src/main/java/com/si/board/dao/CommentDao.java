package com.si.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.si.board.vo.CommentVo;

@Repository
public class CommentDao {

	private final SqlSession session;

	@Autowired
	public CommentDao(@Qualifier("sqlSessionTemplate") SqlSession session) {
		this.session = session;
	}

	public int writeComment(CommentVo vo) {
		return session.insert("CommentMapper.writeComment", vo);
	}

	public List<CommentVo> getCommentByPost(Map<String, Object> map) {
		return session.selectList("CommentMapper.getCommentByPost", map);
	}
}
