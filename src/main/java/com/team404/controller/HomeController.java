package com.team404.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}

	// 타일즈 템플릿 요청
	@RequestMapping("/aaa")
	public String aaa() {
		return "test/aaa";
	}
	
	@RequestMapping("/bbb")
	public String bbb() {
		return "test/bbb";
	}
	
	// tiles view를 직접 연결하는 요청
	// header를 달지 않고 출력된다	
	@RequestMapping("/zzz")
	public String zzz() {
		return "zzz";
	}
	
	@RequestMapping("/kkk")
	public String kkk() {
		return "kkk";
	}
	
}
