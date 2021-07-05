package com.team404.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestTestVO {

	private String adminAuth; // 관리자 인증키
	private String employeeId; // 사원코드
	private String department; // 부서명 
	private int salary; // 급여
	private String joinDate; // 입사 일자
}
