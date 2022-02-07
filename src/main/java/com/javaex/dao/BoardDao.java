package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//방명록 기본리스트 불러오기
	public List<BoardVo> getList() {
		System.out.println("[BoardDao.getList()]");
		
		return sqlSession.selectList("board.getList");

	}
	
	//리스트 가져오기(리스트 + 페이징)
	public List<BoardVo> selectList2(int startRnum, int endRnum) {
		System.out.println("[boardDao/selectList2()]");
		System.out.println(startRnum+ "," +endRnum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		return sqlSession.selectList("board.getList2", map);
	}
	
	//글저장
	public int boardInsert(BoardVo boardVo) {
		System.out.println("[BoardDao.boardInsert()]");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//글삭제
	public int boardDelete(int no) {
		System.out.println("[BoardDao.boardDelete()]");
		
		return sqlSession.delete("board.delete", no);
	}
	
	//글읽기
	public BoardVo read(int no) {
		System.out.println("[BoardDao.read()]");
		
		return sqlSession.selectOne("board.read", no);
	}
	
	//글수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardDao.boardUpdate()]");
		
		return sqlSession.update("board.update", boardVo);
	}
	
	//조회수 업데이트
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");
		
		return sqlSession.update("board.updateHit", no);
	}
	
	//전체 글갯수 가져오기
	public int selectTotal() {
		System.out.println("[BoardDao.selectTotal()]");
		
		return sqlSession.selectOne("board.totalCnt");
	}

}
