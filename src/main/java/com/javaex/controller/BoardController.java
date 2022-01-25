package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//방명록 기본리스트 불러오기
	@RequestMapping(value="/board/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String getList(BoardVo boardVo, Model model) {
		System.out.println("[BoardController.getList()]");
		
		List<BoardVo> bList = boardService.getList(boardVo);
		model.addAttribute("bList", bList);

		return "board/list";
	}
	
	//글쓰기 폼
	@RequestMapping(value="/board/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[BoardController.write()]");
		
		return "board/writeForm";
	}
	
	//글쓰기
	@RequestMapping(value="/board/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(BoardVo boardVo) {
		System.out.println("[BoardController.write()]");
		
		
		
		return "";
	}

}
