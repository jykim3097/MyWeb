package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardMapper {

	public int regist(FreeBoardVO vo); //등록
	// select bno
	public int getBno(FreeBoardVO vo);
	
	//조회 기본 방법
//	public ArrayList<FreeBoardVO> getList(); //조회
	
	public int getTotal(Criteria cri); // 전체 게시글 수
	
	// 페이징 - criteria 적용 방법
	public ArrayList<FreeBoardVO> getList(Criteria cri);
	
	public FreeBoardVO getDetail(int bno); // 상세 화면
	public int update(FreeBoardVO vo); //변경
	public int delete(int bno); //삭제
}
