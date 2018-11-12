package com.si.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si.board.dao.UserDao;
import com.si.board.service.UserService;
import com.si.board.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 사용자 생성
	 * 
	 * @author l2jong
	 * @since 2018-11-06
	 * @param userVo
	 * @return
	 */
	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
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
