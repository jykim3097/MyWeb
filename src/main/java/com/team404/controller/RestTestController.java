package com.team404.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.team404.command.RestTestVO;

public class RestTestController {

	@GetMapping("/getPath/{adminAuth}/{employeeId}")
	public HashMap<String, RestTestVO> getPath(@PathVariable("adminAuth") String adminAuth,
											   @PathVariable("employeeId") String employeeId) {
		
		HashMap<String, RestTestVO> map = new HashMap<>();
		
		RestTestVO vo = new RestTestVO("2F38E29B34CZA52A", "E1234", "HR", 3200, "2021-06-29");
		map.put("empInfo", vo);
		
		return map;
	}

}
