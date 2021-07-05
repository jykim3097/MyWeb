package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.FreeReplyVO;
import com.team404.freereply.service.ReplyService;
import com.team404.util.Criteria;

@RestController // 비동기로 선언할 거라서 RestController로 선언한다
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	@Qualifier("replyService")
	private ReplyService replyService;
	
	@PostMapping(value = "/replyRegist", produces = "application/json") // ContextPath/reply/replyRegist 요청이 들어가야 한다
	public int replyRegist(@RequestBody FreeReplyVO vo) {
		
		int result = replyService.regist(vo);
		
		return result;
	}
	
	//210628, key 값 형태를 사용하기 위해 list를 map으로 바꾼다
	//조회 메서드
	@GetMapping("/getList/{bno}/{pageNum}")
	public HashMap<String, Object> getList(@PathVariable("bno") int bno,
										  @PathVariable("pageNum") int pageNum) {

		// 1. 일반 댓글
//		ArrayList<FreeReplyVO> list = replyService.getList(bno);
//		System.out.println(list.toString());
//		
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("list", list);
		
		// 2. 더보기 댓글
		// 1) 화면에 더보기 버튼 생성
		// 2) getList(글번호, criteria)
		// 3) mybatis 인터페이스에 글번호와 criteria에 @Param("이름")으로 다중 값 처리
		// 4) sql 변경
		// 5) 전체 댓글 수를 화면에 전달
		
		Criteria cri = new Criteria(pageNum, 20); // 20개씩 데이터 조회
		
		ArrayList<FreeReplyVO> list = replyService.getList(bno, cri);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);

		// 화면에 전체 댓글 수를 전달한다
		int total = replyService.getTotal(bno);
		map.put("total", total);
		
		
		return map;
	}
	
	// 수정 요청
	@PostMapping(value = "update", produces = "application/json")
	public int update(@RequestBody FreeReplyVO vo ) {
		
		int count = replyService.pwCheck(vo);

		if(count == 1) {
			return replyService.update(vo);
		} else {
			return 0; // 실패
		}
	}
	
	// 삭제 요청
	@PostMapping(value = "delete", produces = "application/json")
	public int delete(@RequestBody FreeReplyVO vo) {
		
		int count = replyService.pwCheck(vo);
		
		if(count == 1) {
			System.out.println(replyService.delete(vo));
			return replyService.delete(vo);
		} else {
			return 0;
		}
		
	}
}
