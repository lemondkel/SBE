package com.si.board.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	/**
	 * 사용자 생성
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	int insertUser(Map<String, Object> map);

	/**
	 * 사용자 삭제
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	int deleteUser(Map<String, Object> map);

	/**
	 * 사용자 수정
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	int updateUser(Map<String, Object> map);

	/**
	 * 사용자 선택
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectUser(Map<String, Object> map);

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