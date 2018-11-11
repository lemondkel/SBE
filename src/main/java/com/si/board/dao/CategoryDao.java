package com.si.board.dao;

import com.si.board.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {

	private final SqlSession session;

	@Autowired
	public CategoryDao(@Qualifier("sqlSessionTemplate") SqlSession session) {
		this.session = session;
	}

	/**
	 * 해당 게시판에 있는 모든 카테고리를 가져옵니다.
	 *
	 * @param boardSeq 게시판 번호
	 * @return List
	 * @author l2jong
	 * @since 2018-11-12
	 */
	public List<CategoryVo> getAllCategoryByBoard(int boardSeq) {
		return session.selectList("CategoryMapper.getAllCategoryByBoard", boardSeq);
	}

	;
}
