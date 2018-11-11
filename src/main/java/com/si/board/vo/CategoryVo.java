package com.si.board.vo;

import org.springframework.stereotype.Component;

@Component
public class CategoryVo {

	private int categorySeq; // 카테고리 시퀀스 번호
	private String categoryName; // 카테고리 이름
	private int categoryOrder; // 카테고리 순번

	public int getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

}
