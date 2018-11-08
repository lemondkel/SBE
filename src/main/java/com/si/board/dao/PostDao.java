package com.si.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.si.board.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession session;

	public int insertPost(PostVo postVo) {
		return session.insert("PostMapper.insertPost", postVo);
	}

	public List<PostVo> getPostByBoard(Map<String, Object> map) {
		return session.selectList("PostMapper.getPostByBoard", map);
	}

	public List<PostVo> getPostByCategory(Map<String, Object> map) {
		return session.selectList("PostMapper.getPostByCategory", map);
	};
}
