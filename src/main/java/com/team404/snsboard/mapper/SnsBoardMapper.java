package com.team404.snsboard.mapper;

import java.util.ArrayList;

import com.team404.command.SnsBoardVO;

public interface SnsBoardMapper {

	public int insert(SnsBoardVO vo); // insert file
	public ArrayList<SnsBoardVO> getList();
}
