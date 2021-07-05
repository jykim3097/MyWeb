package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;
import com.team404.util.Criteria;
import com.team404.util.PageVO;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	//리스트화면
	@RequestMapping("/freeList")
	public String freeList(Model model, Criteria cri) {

		// 게시판 기본 호출 방법
//		ArrayList<FreeBoardVO> list = freeBoardService.getList();
//		
//		// 화면으로 값을 전달, 하는 basic한 방법 : Model
//		// 값이 많아지면 map으로 가능
//		model.addAttribute("list", list);
		
		//페이징(210623)
		//처음에는 1~10이 나갈 것
//		ArrayList<FreeBoardVO> list = freeBoardService.getList(cri);
//		
//		int total = freeBoardService.getTotal();
//		PageVO pageVO = new PageVO(cri, total);
//		
//		model.addAttribute("pageVO", pageVO);
//		model.addAttribute("list", list);
		
		// 검색 페이징 - 검색 조건(키워드)에 따라서 게시글 수(total)와 데이터가 바껴야 한다
//		System.out.println(cri.toString());
		
		ArrayList<FreeBoardVO> list = freeBoardService.getList(cri);
		int total = freeBoardService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		
		return "freeBoard/freeList";
	}
	
	// 등록화면
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		
		return "freeBoard/freeRegist";
	}
	
	// 등록요청
	@RequestMapping("/registForm")
	public String registForm(FreeBoardVO vo,
								RedirectAttributes RA) {
		
		int result = freeBoardService.regist(vo); // 성공 시 1, 실패 시 0 반환
		
		if(result == 1) RA.addFlashAttribute("msg", "등록되었습니다.");
		else RA.addFlashAttribute("msg", "등록하지 못했습니다. 다시 시도하세요");

		// forward보다는 그냥 컨트롤러를 태우는 게 낫다
		return "redirect:/freeBoard/freeList";
	}
	
//	@ResponseBody
//	@PostMapping("/registForm")
//	public HashMap<String, Object> registForm(@RequestBody FreeBoardVO vo) {
//		
//		System.out.println(vo.toString());
//		
//		int result = freeBoardService.regist(vo);
//		HashMap<String, Object> map = new HashMap<>();
//		
//		if(result == 1) { // 등록 성공
//			map.put("vo", vo);
//		} else {
//			System.out.println("등록 실패");
//		}
//		
//		return map;
//	}

	
	
	
//	// 상세화면
//	@RequestMapping("/freeDetail")
//	public String freeDetail(@RequestParam("bno") int bno,
//							Model model) {
//		
//		// 메서드 이름 :: getDetail()
//		// sql문을 이용해 FreeBoardVO의 값을 반환 받아
//		// 화면에서 사용할 수 있도록 boardVO로 model을 전달하고 화면에 처리
//		
//		FreeBoardVO vo = freeBoardService.getDetail(bno);
//		model.addAttribute("vo", vo);
//		
//		return "freeBoard/freeDetail";
//	}
//	
//	// 변경화면
//	@RequestMapping("/freeModify")
//	public String freeModify(@RequestParam("bno") int bno,
//								Model model) {
//		
//		FreeBoardVO vo = freeBoardService.getDetail(bno);
//		model.addAttribute("vo", vo);
//		
//		return "freeBoard/freeModify";
//	}
	
	// 상세화면과 변경화면은 내용이 동일하기 때문에 하나로 묶어서 사용한다
	@RequestMapping({"/freeDetail", "/freeModify"})
	public void getDetail(@RequestParam("bno") int bno, Model model) {
		
		FreeBoardVO vo = freeBoardService.getDetail(bno);
		model.addAttribute("vo", vo);
	}
	
	// 글 업데이트 처리
	@RequestMapping("/freeUpdate")
	public String freeUpdate(FreeBoardVO vo,
								RedirectAttributes RA) {
		
		/*
		 * 1. form에서 넘어오는 값을 받는다
		 * 2. update()를 이용해 게시글을 수정 처리
		 * 3. update() 메서드는 성공 or 실패의 결과를 받아온다
		 * 4. list 화면으로 msg를 담아서 이동
		 */
		
		int result = freeBoardService.update(vo);
		
		if(result == 1) RA.addFlashAttribute("msg", "업데이트가 정상 처리되었습니다.");
		else RA.addFlashAttribute("msg", "업데이트하지 못했습니다.");
		
		return "redirect:/freeBoard/freeList";
	}
	
	// 글 삭제
	@RequestMapping("/freeDelete")
	public String freeDelete(@RequestParam("bno") int bno,
								RedirectAttributes RA) {
		
		int result = freeBoardService.delete(bno);
		
		if(result == 1) RA.addFlashAttribute("msg", "삭제되었습니다.");
		else RA.addFlashAttribute("msg", "삭제하지 못했습니다.");
		
		return "redirect:/freeBoard/freeList";
	}
	
	
}
