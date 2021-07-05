package com.team404.freeboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.mapper.FreeBoardMapper;
import com.team404.util.Criteria;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardMapper freeBoardMapper;
	
	@Override
	public int regist(FreeBoardVO vo) {
		
		return freeBoardMapper.regist(vo);		
	}

//	@Override
//	public ArrayList<FreeBoardVO> getList() {
//		
//		return freeBoardMapper.getList();
//	}
	
	@Override
	public int getTotal(Criteria cri) {
		return freeBoardMapper.getTotal(cri);
	}

	@Override
	public ArrayList<FreeBoardVO> getList(Criteria cri) {
		return freeBoardMapper.getList(cri);
	}
	
	@Override
	public FreeBoardVO getDetail(int bno) {
		return freeBoardMapper.getDetail(bno);
	}

	@Override
	public int update(FreeBoardVO vo) {
		return freeBoardMapper.update(vo);
	}

	@Override
	public int delete(int bno) {
		
		return freeBoardMapper.delete(bno);
	}
}
