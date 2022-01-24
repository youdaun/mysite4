package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	// 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("[UserService.login()]");
		
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	}
	
	// 회원가입
	public void join(UserVo userVo) {
		System.out.println("[UserService.join()]");
		
		userDao.insertUser(userVo);
	}
	
	// 회원수정폼
	public UserVo modifyForm(int no) {
		System.out.println("[UserService.modifyForm()]");
		
		UserVo getUser = userDao.getUser(no);
		
		return getUser;
	}
	
	// 회원수정
	public void modify(UserVo userVo) {
		System.out.println("[UserService.modify()]");
		
		userDao.updateUser(userVo);
	}
	
	
	
}
