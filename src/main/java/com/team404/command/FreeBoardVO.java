package com.team404.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// getter, setter, 기본 생성자, 모든멤버변수생성자 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private Timestamp regdate;
	private Timestamp updatedate;
}
