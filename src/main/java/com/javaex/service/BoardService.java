package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//방명록 기본리스트 불러오기
	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList()]");
		
		return boardDao.getList();
	}
	
	//리스트(리스트 + 페이징)
	public Map<String, Object> getList2(int crtPage){
		System.out.println("[BoardService.getList2()]");
		
		//////////////////////////////////////////
		// 리스트 가져오기
		//////////////////////////////////////////
		
		//페이지당 글개수(1페이지에 몇개)
		int listCnt = 10;
		
		//현재페이지 처리(0이하 숫자들어올때)
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		/* 위에 3항 연산자랑 같은뜻
		if(crtPage <= 0) {
			crtPage = 1;
		}
		*/
		
		//시작글 번호
		int startRnum = (crtPage-1) * listCnt + 1;
		
		//끝글 번호
		int endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> bList = boardDao.selectList2(startRnum, endRnum);
		
		//////////////////////////////////////////
		// 페이징 버튼
		//////////////////////////////////////////
		
		//전체 글갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//끝 버튼 번호
			//1p   1~5   0.2 
			//2p   1~5   0.4
			//3p   1~5   0.6
			//5p   1~5   1
			//6p   6~10  1.2
			//10p  6~10
			//11p  11~15
		int endPageBtnNo = (int)(Math.ceil(crtPage/(double)pageBtnCount))*pageBtnCount; 
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음(next) 화살표 유무
		boolean next = false;
		if(endPageBtnNo * listCnt < totalCnt) {
			next = true;
		}else { //다음 화살표가 안보이면 마지막 버튼값을 다시계산
			endPageBtnNo = (int)(Math.ceil(totalCnt/(double)listCnt));
		}
		
		//이전(prev) 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//////////////////////////////////////////
		// 리턴값 포장
		//////////////////////////////////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("bList", bList);
		
		return pMap;
	}
	
	//글저장
	public void write(BoardVo boardVo) {
		System.out.println("[BoardService.write()]");
		
		//페이징 데이터 추가 123개
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i + "번째글 제목입니다.");
			boardVo.setContent(i + "번째글 내용입니다.");
			boardVo.setUserNo(1);
			boardDao.boardInsert(boardVo);		
		}
		
		//boardDao.boardInsert(boardVo);
	}
	
	//글삭제
	public void delete(int no) {
		System.out.println("[BoardService.delete()]");
		
		boardDao.boardDelete(no);
	}
	
	//글읽기, 글수정폼
	public BoardVo read(int no) {
		System.out.println("[BoardService.read()]");
		
		return boardDao.read(no);
	}
	
	//글수정
	public void modify(BoardVo boardVo) {
		System.out.println("[BoardService.modify()]");
		
		boardDao.boardUpdate(boardVo);
	}
	
	//조회수 업데이트
	public void updateHit(int no) {
		System.out.println("[BoardService.updateHit()]");
		
		boardDao.updateHit(no);
	}

}
