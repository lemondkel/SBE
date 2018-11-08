package com.si.board.vo;

import org.springframework.stereotype.Component;

@Component
public class PostVo {

	private int boardSeq;
	private int categorySeq;
	private int postSeq;
	private int postViewCount;
	private String postTitle;
	private String postContents;
	private String postRegUserId;
	private String postRegUserName;
	private String postRegDate;
	private String postCategoryName;

	public int getPostSeq() {
		return postSeq;
	}

	public void setPostSeq(int postSeq) {
		this.postSeq = postSeq;
	}

	public int getPostViewCount() {
		return postViewCount;
	}

	public void setPostViewCount(int postViewCount) {
		this.postViewCount = postViewCount;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostRegUserName() {
		return postRegUserName;
	}

	public void setPostRegUserName(String postRegUserName) {
		this.postRegUserName = postRegUserName;
	}

	public String getPostRegDate() {
		return postRegDate;
	}

	public void setPostRegDate(String postRegDate) {
		this.postRegDate = postRegDate;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}

	public String getPostContents() {
		return postContents;
	}

	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}

	public String getPostRegUserId() {
		return postRegUserId;
	}

	public void setPostRegUserId(String postRegUserId) {
		this.postRegUserId = postRegUserId;
	}

	public String getPostCategoryName() {
		return postCategoryName;
	}

	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}
}