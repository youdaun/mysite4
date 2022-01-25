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
	
	//글저장
	public void write(BoardVo boardVo) {
		System.out.println("[BoardService.write()]");
		
		boardDao.boardInsert(boardVo);
	}
	
	//글삭제
	public void delete(int no) {
		System.out.println("[BoardService.delete()]");
		
		boardDao.boardDelete(no);
	}
	
	//글읽기, 글수정폼
	public BoardVo read(int no) {
		System.out.println("[BoardService.read()]");
		
		return boardDao.read(no);
	}
	
	//글수정
	public void modify(BoardVo boardVo) {
		System.out.println("[BoardService.modify()]");
		
		boardDao.boardUpdate(boardVo);
	}
	
	//조회수 업데이트
	public void updateHit(int no) {
		System.out.println("[BoardService.updateHit()]");
		
		boardDao.updateHit(no);
	}

}
