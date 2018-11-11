package com.si.board.impl;

import com.si.board.dao.CategoryDao;
import com.si.board.service.CategoryService;
import com.si.board.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 해당 게시판의 모든 카테고리를 가져온다.
	 *
	 * @param boardSeq 게시판 번호
	 * @return List
	 * @author l2jong
	 * @since 2018-11-12
	 */
	@Override
	public List<CategoryVo> getAllCategoryByBoard(int boardSeq) {
		return categoryDao.getAllCategoryByBoard(boardSeq);
	}

}