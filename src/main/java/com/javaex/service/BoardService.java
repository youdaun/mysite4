package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//방명록 기본리스트 불러오기
	public List<BoardVo> getList(BoardVo boardVo) {
		System.out.println("[BoardService.getList()]");
		
		return boardDao.getList(boardVo);
	}

}
