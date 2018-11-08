package com.si.board.vo;

import org.springframework.stereotype.Component;

/**
 * 회원 VO
 * 
 * @author l2jong
 * @since 2018-11-06
 *
 */
@Component
public class UserVo {

	private String userId;
	private String userPassword;
	private String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}