package com.si.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession session;

	/**
	 * 사용자 생성
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	public int insertUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.insert("UserMapper.insertUser", map);
	}

	/**
	 * 사용자 삭제
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	public int deleteUser(Map<String, Object> map) {
		return 0;
	}

	/**
	 * 사용자 수정
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	public int updateUser(Map<String, Object> map) {
		return 0;
	}

	/**
	 * 사용자 선택
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectUser(Map<String, Object> map) {
		return null;
	}

	/**
	 * 사용자의 암호화된 비밀번호를 가져온다.
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userId
	 * @return
	 */
	public String getUserEncodedPassword(String userId) {
		return session.selectOne("UserMapper.getUserEncodedPassword", userId);
	}

	/**
	 * 해당 아이디를 가진 사용자가 있는지 없는지 검사합니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userId
	 * @return
	 */
	public boolean isExistUser(String userId) {
		return session.selectOne("UserMapper.isExistUser", userId);
	}

}
