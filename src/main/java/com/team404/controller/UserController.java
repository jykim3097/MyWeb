package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.UserVO;
import com.team404.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	// 가입화면
	@RequestMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	//로그인 화면
	@RequestMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	// 마이페이지화면
	@RequestMapping("/userMypage")
	public String userMypage(HttpSession session, Model model) {
		
//		if(session.getAttribute("userVO") == null ) { // 로그인이 안됐다면
//			return "redirect:/user/userLogin";
//		}
		
		// session에 있는 user id를 가져온다
		UserVO vo = (UserVO)session.getAttribute("userVO");
		String userId = vo.getUserId();
		
		//회원 정보 조회
		UserVO userInfo = userService.getInfo(userId);
		model.addAttribute("userInfo", userInfo);
		
		return "user/userMypage";
	}
	
	// 중복 처리 메서드
	@ResponseBody
	@PostMapping(value = "/idCheck", produces = "application/json")
	public int idCheck(@RequestBody UserVO vo) {
		
		int result = userService.idCheck(vo);
		
		return result; // 0이면 중복 x, 1이라면 중복
	}
	
	// 가입요청
	@RequestMapping(value= "/joinForm", method = RequestMethod.POST)
	public String joinForm(UserVO vo,
						   RedirectAttributes RA) {
		
		int result = userService.join(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "가입을 축하합니다.");
		} else { //가입 실패
			RA.addFlashAttribute("msg", "가입에 실패했습니다.");
		}
		
		return "redirect:/user/userLogin";
	}
	
	// 로그인 요청
//	@RequestMapping(value="/loginForm", method = RequestMethod.POST)
//	public String loginForm(UserVO vo, HttpSession session, Model model) {
//		
////		System.out.println(vo.toString());
//		
//		// 로그인 처리
//		UserVO userVO = userService.login(vo);
//		System.out.println(userVO);
//		
//		if( userVO != null) { // 로그인 성공
//			
//			session.setAttribute("userVO", userVO); // 회원 정보 저장
//			return "redirect:/"; //home으로
//			
//		} else { // 로그인 실패
//			
//			model.addAttribute("msg", "아이디와 비밀번호를 확인하세요");
//			return "user/userLogin";
//		}
//	}
	
	// PostHandler를 이용한 로그인 요청 210630
	@RequestMapping(value="/loginForm", method = RequestMethod.POST)
	public ModelAndView loginForm(UserVO vo) {
		
		UserVO userVO = userService.login(vo);
		
		ModelAndView mv = new ModelAndView();
		
		if(userVO != null)	mv.addObject("login", userVO); //로그인 성공
		else mv.addObject("msg", "아이디나 비밀번호를 확인하세요");// 로그인 실패
		
		return mv; // 디스패처서블릿으로 반환
	}
	
	// 로그아웃
	@RequestMapping("/userLogout")
	public String userLogout(HttpSession session) {
		
		session.invalidate(); // 인증 정보 삭제 (세션 무효화)
		
		return "redirect:/"; // home으로 이동
	}
	
	
}
