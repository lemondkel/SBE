package com.si.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.si.board.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession session;

	public List<CategoryVo> getAllCategoryByBoard(int boardSeq) {
		return session.selectList("CategoryMapper.getAllCategoryByBoard", boardSeq);
	};
}
