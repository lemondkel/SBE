package com.si.board.controller.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 회원에 관련된 비즈니스 로직을 담고 있는 컨트롤러
 * 
 * @author l2jong
 * @since 2018-11-05
 *
 */
@RequestMapping("/user/process")
@Controller
public class UserProcessController {

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	/**
	 * 회원가입 페이지입니다.
	 * 
	 * @author l2jong
	 * @since 2018-11-05
	 * @param locale
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userJoinPage(@RequestParam("user_id") String userId,
			@RequestParam("user_password") String userPassword) {
		System.out.println(bCryptPasswordEncoder.encode("asdasd"));

		return "user/join";
	}
}