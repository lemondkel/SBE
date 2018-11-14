package com.si.board.dao;

import com.si.board.vo.PostVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostDao {

	private final SqlSession session;

	@Autowired
	public PostDao(@Qualifier("sqlSessionTemplate") SqlSession session) {
		this.session = session;
	}

	/**
	 * 게시물 작성
	 *
	 * @param postVo 게시물 VO
	 * @return
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public int insertPost(PostVo postVo) {
		return session.insert("PostMapper.insertPost", postVo);
	}

	/**
	 * 해당 게시판에 있는 게시물들을 가져옵니다.
	 *
	 * @param map
	 * @return
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public List<PostVo> getPostByBoard(Map<String, Object> map) {
		return session.selectList("PostMapper.getPostByBoard", map);
	}

	/**
	 * 해당 게시판에 있는 게시물 총합을 가져옵니다.
	 *
	 * @param map
	 * @return
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public int getPostByBoardSum(int boardSeq) {
		return session.selectOne("PostMapper.getPostByBoardSum", boardSeq);
	}

	/**
	 * 해당 카테고리에 있는 게시물들을 가져옵니다.
	 *
	 * @param map
	 * @return
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public List<PostVo> getPostByCategory(Map<String, Object> map) {
		return session.selectList("PostMapper.getPostByCategory", map);
	}

	/**
	 * 게시물을 삭제합니다.
	 *
	 * @param postSeq 게시물 번호
	 * @return int
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public int deletePost(int postSeq) {
		return session.delete("PostMapper.deletePost", postSeq);
	}

	/**
	 * 해당 사용자가 해당 게시물을 작성했는지 확인합니다.
	 *
	 * @param map 사용자 정보를 담은 맵
	 * @return boolean
	 * @author l2jong
	 * @since 2018-11-11
	 */
	public boolean isCorrectlyWriter(Map<String, Object> map) {
		return session.selectOne("PostMapper.isCorrectlyWriter", map);
	}

	/**
	 * 게시물을 조회할 때 조회수를 증가시킵니다.
	 *
	 * @param postSeq 게시물 시퀀스 번호
	 * @return int
	 * @author l2jong
	 * @since 2018-11-12
	 */
	public int viewPost(int postSeq) {
		return session.update("PostMapper.viewPost", postSeq);
	}

	/**
	 * 게시물이 존재하는지 확인합니다.
	 *
	 * @param postSeq 게시물 시퀀스 번호
	 * @return boolean
	 * @author l2jong
	 * @since 2018-11-12
	 */
	public boolean isExistPost(int postSeq) {
		return session.selectOne("PostMapper.isExistPost", postSeq);
	}

	/**
	 * 게시물 상세정보를 가져옵니다.
	 *
	 * @param postSeq 게시물 시퀀스 번호
	 * @return PostVo
	 * @author l2jong
	 * @since 2018-11-12
	 */
	public PostVo getPostDetail(int postSeq) {
		return session.selectOne("PostMapper.getPostDetail", postSeq);
	}

	public int getPostByCategorySum(int categorySeq) {
		return session.selectOne("PostMapper.getPostByCategorySum", categorySeq);
	}
}