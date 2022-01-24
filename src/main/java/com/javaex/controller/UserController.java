package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인폼
	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");

		UserVo authUser = userService.login(userVo);
		System.out.println(authUser);

		if (authUser != null) { // 로그인성공
			// 세션에 저장
			session.setAttribute("authUser", authUser);
			// 리다이렉트 메인
			return "redirect:/";

		} else { // 로그인실패
			// 리다이렉트 로그인폼
			return "redirect:user/loginForm";
		}

	}

	// 로그아웃
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()]");
		
		//세션의 정보 삭제
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	// 회원가입폼
	@RequestMapping(value = "/user/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");

		return "user/joinForm";
	}
	
	// 회원가입
	@RequestMapping(value = "/user/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	// 회원수정폼
	@RequestMapping(value = "/user/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("[UserController.modifyForm()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		UserVo userVo = userService.modifyForm(no);
		userVo.setNo(no);
		
		model.addAttribute("uvo", userVo);
		
		return "user/modifyForm";
	}
	
	// 회원수정
	@RequestMapping(value = "/user/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("[UserController.modify()]");

		userService.modify(userVo);
		session.setAttribute("authUser", userVo);
		
		return "redirect:/";
	}
	
	
	
	
	
	
}
