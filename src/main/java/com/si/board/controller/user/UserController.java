package com.si.board.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 회원에 관련된 컨트롤러
 * 
 * @author l2jong
 * @since 2018-11-05
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 회원가입 페이지입니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-05
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userJoinPage() {
		return "user/join";
	}

}
