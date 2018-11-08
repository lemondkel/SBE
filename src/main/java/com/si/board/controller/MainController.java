package com.si.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 메인페이지 뷰 컨트롤러입니다.
 * 
 * @author l2jong
 * @since 2018-11-06
 *
 */
@Controller
public class MainController {

	/**
	 * 메인페이지
	 * 
	 * @author l2jong
	 * @since 2018-11-05
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage() {
		return "main";
	}
}