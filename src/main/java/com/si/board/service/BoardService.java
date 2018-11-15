package com.si.board.service;

import java.util.List;

import com.si.board.vo.BoardVo;

public interface BoardService {

	List<BoardVo> getAllBoard();

	boolean isExistBoard(int boardSeq);

	String getBoardName(int boardSeq);

}