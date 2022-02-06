package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;

	public String restore(MultipartFile file, GalleryVo gvo) {
		System.out.println("GalleryService.restore()");
		String saveDir = "C:\\javaStudy\\upload";

		// 파일을 하드디스크에 저장(운영내용)

		// 원본파일이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		// System.out.println(saveName);

		// 파일패쓰 생성
		String filePath = saveDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		// ***파일저장(업로드)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB에 저장
		gvo.setFilePath(filePath);
		gvo.setOrgName(orgName);
		gvo.setSaveName(saveName);
		gvo.setFileSize(fileSize);
		gvo.setContent(orgName);

		galleryDao.insertFile(gvo);

		return saveName;
	}

	public List<GalleryVo> getList() {
		return galleryDao.getList();
	}
}
