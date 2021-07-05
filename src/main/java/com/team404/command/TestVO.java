package com.team404.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //toString, getter, stter 자동생성
@AllArgsConstructor // 모든 멤버변수 초기화 생성
@NoArgsConstructor // 기본 생성자
public class TestVO {

	private String name;
	private String age;
	private String date;
	private int birth;
}
