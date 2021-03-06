package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	// 파일업로드 폼
	@RequestMapping("/form")
	public String form() {
		System.out.println("GalleryController.form()");

		return "fileupload/form";
	}

	/*
	 * // 파일업로드 처리
	 * 
	 * @RequestMapping("/upload") public String upload(@RequestParam("file")
	 * MultipartFile file, Model model, GalleryVo gvo) {
	 * System.out.println("GalleryController.upload()");
	 * 
	 * String saveName = galleryService.restore(file, gvo);
	 * model.addAttribute("saveName", saveName);
	 * 
	 * return "fileupload/result"; }
	 */

	// 파일리스트
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GalleryController.list()");

		model.addAttribute("galleryList", galleryService.getList());

		return "gallery/list";
	}

	// 이미지 등록
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, Model model, GalleryVo gvo) {
		System.out.println("GalleryController.upload()");

		String saveName = galleryService.restore(file, gvo);
		model.addAttribute("saveName", saveName);

		return "redirect:/gallery/list";
	}

	// 파일한개만
	@ResponseBody
	@RequestMapping("/viewImg")
	public GalleryVo getImg(@RequestParam("no") int no) {
		System.out.println("GalleryController.viewImg()");

		GalleryVo gvo = galleryService.getImg(no);

		return gvo;
	}

	// 이미지 삭제
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("GalleryController.delete()");

		return galleryService.delete(no);
	}

}
