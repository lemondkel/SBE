package com.si.board.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CommentVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int commentSeq;
	private int postSeq;
	private String commentContents;
	private String commentRegUsrId;
	private String commentRegUsrName;
	private String commentRegDate;
	private String commentEditUsrId;
	private String commentEditDate;

	public int getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public int getPostSeq() {
		return postSeq;
	}

	public void setPostSeq(int postSeq) {
		this.postSeq = postSeq;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCommentRegUsrId() {
		return commentRegUsrId;
	}

	public void setCommentRegUsrId(String commentRegUsrId) {
		this.commentRegUsrId = commentRegUsrId;
	}

	public String getCommentRegDate() {
		return commentRegDate;
	}

	public void setCommentRegDate(String commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

	public String getCommentEditUsrId() {
		return commentEditUsrId;
	}

	public void setCommentEditUsrId(String commentEditUsrId) {
		this.commentEditUsrId = commentEditUsrId;
	}

	public String getCommentEditDate() {
		return commentEditDate;
	}

	public void setCommentEditDate(String commentEditDate) {
		this.commentEditDate = commentEditDate;
	}

	public String getCommentRegUsrName() {
		return commentRegUsrName;
	}

	public void setCommentRegUsrName(String commentRegUsrName) {
		this.commentRegUsrName = commentRegUsrName;
	}
}