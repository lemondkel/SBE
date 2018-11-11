package com.si.board.impl;

import com.si.board.dao.BoardDao;
import com.si.board.service.BoardService;
import com.si.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	/**
	 * 모든 게시판을 가져옵니다.
	 *
	 * @return List
	 * @author l2jong
	 * @since 2018-11-11
	 */
	@Override
	public List<BoardVo> getAllBoard() {
		return boardDao.getAllBoard();
	}

	/**
	 * 게시판이 존재하는지 검증합니다.
	 *
	 * @param boardSeq 게시판 번호
	 * @return boolean
	 * @author l2jong
	 * @since 2018-11-11
	 */
	@Override
	public boolean isExistBoard(int boardSeq) {
		return boardDao.isExistBoard(boardSeq);
	}

}
