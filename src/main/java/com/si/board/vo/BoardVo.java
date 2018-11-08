package com.si.board.vo;

import org.springframework.stereotype.Component;

@Component
public class BoardVo {

	private int boardSeq;
	private String boardName;
	private int boardOrder;

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
