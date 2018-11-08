package com.si.board.service;

import java.util.List;

import com.si.board.vo.CategoryVo;

public interface CategoryService {
	List<CategoryVo> getAllCategoryByBoard(int boardSeq);
}