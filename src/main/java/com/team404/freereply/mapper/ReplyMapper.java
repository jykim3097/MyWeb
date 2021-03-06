package com.team404.freereply.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.FreeReplyVO;
import com.team404.util.Criteria;

public interface ReplyMapper {

	public int regist(FreeReplyVO vo);
//	public ArrayList<FreeReplyVO> getList(int bno);
//	두 개 이상의 모호한 값을 전달할 때는 .., map, param을 이용할 수 있다.
	public ArrayList<FreeReplyVO> getList(@Param("bno") int bno, @Param("cri") Criteria cri);
	public int pwCheck(FreeReplyVO vo); //비밀번호 확인
	public int update(FreeReplyVO vo); //수정
	public int delete(FreeReplyVO vo);
	public int getTotal(int bno);
}
