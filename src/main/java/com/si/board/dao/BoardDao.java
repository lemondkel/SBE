package com.si.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.si.board.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession session;

	public List<BoardVo> getAllBoard() {
		return session.selectList("BoardMapper.getAllBoard");
	}

	public boolean isExistBoard(int boardSeq) {
		return session.selectOne("BoardMapper.isExistBoard", boardSeq);
	};
}
