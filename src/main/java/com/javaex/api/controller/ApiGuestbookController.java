package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping("/addList")
	public String addList() {
		System.out.println("[ApiGuestbookController>addList]");

		return "aGuestbook/addList";
	}

	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("[ApiGuestbookController>list]");

		List<GuestbookVo> guestbookList = guestbookService.getGuestList();
		System.out.println(guestbookList);

		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController>write]");
		
		//저장하고 저장된값 리턴
		GuestbookVo gvo = guestbookService.addGuestResultVo(guestbookVo);
		
		return gvo;
	}
}
