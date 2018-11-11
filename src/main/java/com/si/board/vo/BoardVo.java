package com.si.board.vo;

import org.springframework.stereotype.Component;

@Component
public class BoardVo {

	private int boardSeq; // 게시판 시퀀스 번호
	private String boardName; // 게시판 이름
	private int boardOrder; // 게시판 순번

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getBoardOrder() {
		return boardOrder;
	}

	public void setBoardOrder(int boardOrder) {
		this.boardOrder = boardOrder;
	}
}
