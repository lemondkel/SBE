package com.si.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.board.dao.BoardDao;
import com.si.board.service.BoardService;
import com.si.board.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<BoardVo> getAllBoard() {
		return boardDao.getAllBoard();
	}

	@Override
	public boolean isExistBoard(int boardSeq) {
		return boardDao.isExistBoard(boardSeq);
	}

}
