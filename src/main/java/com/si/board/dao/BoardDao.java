package com.si.board.dao;

import com.si.board.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

	private final SqlSession session;

	@Autowired
	public BoardDao(@Qualifier("sqlSessionTemplate") SqlSession session) {
		this.session = session;
	}

	/**
	 * 모든 게시판을 가져옵니다.
	 *
	 * @return List
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public List<BoardVo> getAllBoard() {
		return session.selectList("BoardMapper.getAllBoard");
	}

	/**
	 * 게시판이 존재하는지 검증합니다.
	 *
	 * @param boardSeq 게시판 번호
	 * @return boolean
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public boolean isExistBoard(int boardSeq) {
		return session.selectOne("BoardMapper.isExistBoard", boardSeq);
	}

	public String getBoardName(int boardSeq) {
		return session.selectOne("BoardMapper.getBoardName", boardSeq);
	}
}