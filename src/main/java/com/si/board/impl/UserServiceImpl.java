package com.si.board.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.board.dao.UserDao;
import com.si.board.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 사용자 생성
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	@Override
	public int insertUser(Map<String, Object> map) {
		return userDao.insertUser(map);
	}

	/**
	 * 사용자 삭제
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	@Override
	public int deleteUser(Map<String, Object> map) {
		return userDao.deleteUser(map);
	}

	/**
	 * 사용자 수정
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	@Override
	public int updateUser(Map<String, Object> map) {
		return userDao.updateUser(map);
	}

	/**
	 * 사용자 선택
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectUser(Map<String, Object> map) {
		return userDao.selectUser(map);
	}

	/**
	 * 사용자의 암호화된 비밀번호를 가져온다.
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userId
	 * @return
	 */
	@Override
	public String getUserEncodedPassword(String userId) {
		return userDao.getUserEncodedPassword(userId);
	}

	/**
	 * 해당 아이디를 가진 사용자가 있는지 없는지 검사합니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userId
	 * @return
	 */
	@Override
	public boolean isExistUser(String userId) {
		return userDao.isExistUser(userId);
	}

}
