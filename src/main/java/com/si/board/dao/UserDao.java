package com.si.board.dao;

import com.si.board.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	private final SqlSession session;

	@Autowired
	public UserDao(@Qualifier("sqlSessionTemplate") SqlSession session) {
		this.session = session;
	}

	/**
	 * 사용자 생성
	 *
	 * @param userVo 사용자 Vo
	 * @return int
	 * @author l2jong
	 * @since 2018-11-06
	 */
	public int insertUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return session.insert("UserMapper.insertUser", userVo);
	}

	/**
	 * 사용자의 암호화된 비밀번호를 가져온다.
	 *
	 * @param userId 사용자 아이디
	 * @return String 암호화된 비밀번호
	 * @author l2jong
	 * @since 2018-11-06
	 */
	public String getUserEncodedPassword(String userId) {
		return session.selectOne("UserMapper.getUserEncodedPassword", userId);
	}

	/**
	 * 해당 아이디를 가진 사용자가 있는지 없는지 검사합니다.
	 *
	 * @param userId 사용자 아이디
	 * @return boolean
	 * @author l2jong
	 * @since 2018-11-06
	 */
	public boolean isExistUser(String userId) {
		return session.selectOne("UserMapper.isExistUser", userId);
	}
}