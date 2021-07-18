package com.team404.util;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum; // 조회하는 페이지 번호
	private int amount; // 한 페이지에 보여줄 컨텐츠 개수
	
	// 검색에 필요한 키워드 선언
	private String searchType;
	private String searchName;
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
