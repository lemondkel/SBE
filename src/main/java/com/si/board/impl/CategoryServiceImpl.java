package com.si.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.board.dao.CategoryDao;
import com.si.board.service.CategoryService;
import com.si.board.vo.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryVo> getAllCategoryByBoard(int boardSeq) {
		return categoryDao.getAllCategoryByBoard(boardSeq);
	}

}
