package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;

	public int insertFile(GalleryVo gvo) {
		System.out.println(gvo);
		return sqlSession.insert("gallery.insert", gvo);
	}

	public List<GalleryVo> getList() {

		return sqlSession.selectList("gallery.getList");
	}
	
	public GalleryVo getImg(int no) {
		
		return sqlSession.selectOne("gallery.getImg", no);
	}
	
	public int delete(int no) {
		
		return sqlSession.delete("gallery.delete", no);
	}
}
