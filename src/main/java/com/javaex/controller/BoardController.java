package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 방명록 기본리스트 불러오기
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String getList(Model model) {
		System.out.println("[BoardController.getList()]");

		List<BoardVo> bList = boardService.getList();
		model.addAttribute("bList", bList);

		return "board/list";
	}

	// 기본리스트(리스트 + 페이징)
	@RequestMapping(value = "/list2", method = { RequestMethod.GET, RequestMethod.POST })
	public String getList2(Model model, 
						   @RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("[BoardController.getList2()]");
		System.out.println(crtPage);
		
		//해당페이지의 글 리스트 10개
		Map<String, Object> pMap = boardService.getList2(crtPage);
		
		model.addAttribute("pMap", pMap);
	
		return "board/list";
	}

	// 글쓰기 폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[BoardController.writeForm()]");

		return "board/writeForm";
	}

	// 글쓰기
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(BoardVo boardVo) {
		System.out.println("[BoardController.write()]");

		boardService.write(boardVo);

		return "redirect:/board/list";
	}

	// 글삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam int no) {

		System.out.println("[BoardController.delete()]");

		boardService.delete(no);

		return "redirect:/board/list";
	}

	// 글읽기
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam int no, Model model) {
		System.out.println("[BoardController.read()]");

		BoardVo bvo = boardService.read(no);
		model.addAttribute("bvo", bvo);

		// 조회수 업데이트
		boardService.updateHit(no);

		return "board/read";
	}

	// 글수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(int no, Model model) {
		System.out.println("[BoardController.modifyForm()]");

		BoardVo bvo = boardService.read(no);
		model.addAttribute("bvo", bvo);

		return "board/modifyForm";
	}

	// 글수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(BoardVo boardVo) {
		System.out.println("[BoardController.modify()]");

		boardService.modify(boardVo);

		return "redirect:/board/list";
	}

}
