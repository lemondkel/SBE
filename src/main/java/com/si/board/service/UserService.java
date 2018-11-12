package com.si.board.service;

import com.si.board.vo.UserVo;

public interface UserService {

	/**
	 * 사용자 생성
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userVo
	 * @return
	 */
	int insertUser(UserVo userVo);

	/**
	 * 사용자의 암호화된 비밀번호를 가져온다.
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userId
	 * @return
	 */
	String getUserEncodedPassword(String userId);

	/**
	 * 해당 아이디를 가진 사용자가 있는지 없는지 검사합니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userId
	 * @return
	 */
	boolean isExistUser(String userId);
}
