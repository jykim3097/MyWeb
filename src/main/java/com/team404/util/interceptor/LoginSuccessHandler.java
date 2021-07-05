package com.team404.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team404.command.UserVO;

public class LoginSuccessHandler extends HandlerInterceptorAdapter{

	// 로그인 이후에 실행되는 PostHandler 오버라이딩
	// 로그인 요청이 들어올 때 실행되도록 핸들러 등록
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// 매개변수 modelAndView에는 컨트롤러에서 반환하는 mv 객체가 들어있다.
		ModelMap mv = modelAndView.getModelMap(); // modelAndView에 들어있는 정보를 map 형식으로 바꾼다
		
		UserVO userVO = (UserVO)mv.get("login");
		
		if(userVO != null) {
			
			//세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
			
			response.sendRedirect( request.getContextPath() ); //홈화면으로
		}
		
		// 리다이렉트를 만나더라도 뷰네임은 지정이 필요하다
		modelAndView.setViewName("user/userLogin");
	}

}
