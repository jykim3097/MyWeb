package com.team404.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team404.command.UserVO;

public class UserAuthHandler extends HandlerInterceptorAdapter {

	// 1. HandlerInterceptorAdapter를 상속받고 prehandler, postHandler 메서드를 오버라이딩

	// controller 진입 전 요청 핸들러
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		// 세션에서 로그인될 때 생성되는 userVO를 얻는다
		HttpSession session = request.getSession(); // 현재 세션을 얻는다
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		// userVO가 없다 = 로그인 실패
		// 리턴에 true가 들어가면 컨트롤러를 그대로 실행, false가 들어가면 컨트롤러를 실행하지 않는다
		if( userVO == null ) {
			
			response.sendRedirect( request.getContextPath() + "/user/userLogin" );
			return false;
			
		} else return true;
	}

	
	
}
