package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.TestVO;

@RestController // 비동기 전용 컨트롤러( @RequestBody + @ResponseBody )
public class RestBasicCotroller {

	/*
	 * 1. RestController는 기본적으로 return에 담기는 값이 요청된 주소로 반환된다. 리졸버뷰로 가는 게 아니다.
	 * 2. Rest API에서 produces는 보내는 데이터에 대한 내용
	 * 				 consumer는 전달받는 데이터에 대한 내용
	 */
	
	// 일반적인 form 요청을 받는 애가 아니고 비동기 요청을 받기 때문에
	// 값을 화면하고 싶다면 화면에서도 비동기로 요청해야 하고 확인할 때도 비동기로 확인해야 한다
//	@RequestMapping(value = "/getText", method=RequestMethod.GET)
	@GetMapping(value = "/getText", produces = "text/palin") // get 요청에 대해서만 ~, 위에 것과 같은 의미
	public String getText() {
		
		System.out.println("실행됨");
		
		return "hello world";
	}
	
	// java에서 json 형식을 받아 json 형식으로 보낼 때 jackson 라이브러리가 반드시 필요하다
	// maven repository에서 받는다 > jackson
	// 데이터가 json형식이라면 produces 값을 생략할 수 있다.
	@GetMapping(value = "/getObject", produces = "application/json")
	public TestVO getObject() {
		
		return new TestVO("홍길동", "20", "2021", 2000);
	}
	
	
	// 단일 값을 받고, 객체를 반환
	// get 방식이기 때문에 주소에 실어서 보낸다 - 요청방식 : /getCollection?num=값
	@GetMapping(value = "/getCollection")
	public ArrayList<TestVO> getCollection(@RequestParam("num") String num) {
		
		System.out.println("받은 값 : " + num);
		
		ArrayList<TestVO> list = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			TestVO vo = new TestVO("홍길동", (20+i)+"", "2020", 2000);
			list.add(vo);
		}
		
		return list;
	}
	
	// 값/값/값/ 형태로 받고 Map으로 반환 
	// 주소의 형태가 getPath/xxx/xxx/xxx
	@GetMapping("/getPath/{sort}/{rank}/{page}")
	public HashMap<String, TestVO> getPath(@PathVariable("sort") String sort,
										   @PathVariable("rank") String rank,
										   @PathVariable("page") int page) {
		
		System.out.println(sort);
		System.out.println(rank);
		System.out.println(page);
		
		HashMap<String, TestVO> map = new HashMap<>();
		
		TestVO vo = new TestVO("홍길동", "20", "2020", 20);
		map.put("info", vo);
		
		return map;
	}
	
	
	// 포스트형식의 JSON 데이터를 받는다.
	// TestVO에 맵핑될 값을 클라이언트의 payload에 담아서 보낸다
	// 그냥 실행하면 json 값을 vo로 맵핑하지 못한다
	// 그래서 @RequestBody를 써서 요청값의 키:값을 뽑아 vo에 자동으로 맵핑하게 만든다
	// 이 작업을 jsp로도 할 수 있지만 요청 header를 분해하고 어쩌고 해야 돼서 최소 20줄 이상의 작업을 수동으로 해야 한다
	// 이 작업을 @RequestBody로 한 번에 해줄 수 있기 때문에 매우 중요하다
	// consumer를 작성하게 되면, 해당 데이터 타입이 아닐 때 요청을 거절한다
	@PostMapping(value = "/getJson", consumes = "application/json")
	public ArrayList<TestVO> getJson(@RequestBody TestVO vo) {
		
		System.out.println(vo.toString());
		
		ArrayList<TestVO> list = new ArrayList<>();
		TestVO t = new TestVO("홍길동", "20", "2020", 20);
		list.add(t);
		
		return list;
		
	}
	
	// 210625, vscode와 연동
	// vscode가 consumer, sts가 produce
	@CrossOrigin(origins = "*")
	// consumes - json으로 보내라, produces - json으로 보낸다
	@PostMapping(value = "/getAjax", consumes = "application/json", produces = "application/json")
	public ArrayList<TestVO> getAjax(@RequestBody TestVO vo) {
		
		System.out.println(vo.toString());
		
		ArrayList<TestVO> list = new ArrayList<>();
		TestVO t = new TestVO("홍길동", "20", "2020", 20);
		list.add(t);
		
		return list;
	}
	
	// xml 형식으로 반환 (jackson-xml 라이브러리가 반드시 필요)
	@CrossOrigin(origins = "*")
	// consumes - json으로 보내라
	// produces - xml로 반환한다
	@PostMapping(value = "/getXML", consumes = "application/json", produces = "application/xml")
	public ArrayList<TestVO> getXml(@RequestBody TestVO vo) {
		
		System.out.println(vo.toString());
		
		ArrayList<TestVO> list = new ArrayList<>();
		TestVO t = new TestVO("홍길동", "20", "2020", 20);
		list.add(t);
		
		return list;
	}
}
