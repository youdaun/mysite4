package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 유저정보가져오기(로그인시 사용)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
	}
	
	// 회원정보저장(회원가입)
	public int insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser()]");
		
		return sqlSession.insert("user.insertUser", userVo);
	}
	
	// 유저1인정보가져오기(회원수정폼)
	public UserVo getUser(int no) {
		System.out.println("[UserDao.getUser()]");
		
		return sqlSession.selectOne("user.getUser", no);
	}
	
	// 회원수정
	public int updateUser(UserVo userVo) {
		System.out.println("[UserDao.updateUser()]");
		
		return sqlSession.update("user.updateUser", userVo);
	}

}
