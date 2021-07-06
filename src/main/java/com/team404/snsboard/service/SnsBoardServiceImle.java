package com.team404.snsboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.SnsBoardVO;
import com.team404.snsboard.mapper.SnsBoardMapper;

@Service("snsBoardService")
public class SnsBoardServiceImle implements SnsBoardService {

	@Autowired
	private SnsBoardMapper snsBoardMapper;
	
	@Override
	public int insert(SnsBoardVO vo) {
		
		return snsBoardMapper.insert(vo);
	}

}
