package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//방명록 기본리스트 불러오기
	public List<BoardVo> getList(BoardVo boardVo) {
		System.out.println("[BoardDao.getList()]");
		
		return sqlSession.selectList("board.getList", boardVo);

	}

}
