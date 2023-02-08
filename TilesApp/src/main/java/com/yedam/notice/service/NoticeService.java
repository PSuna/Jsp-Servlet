package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeService {

	public List<NoticeVO> noticeList();
	public NoticeVO getNotice(int nid); // 한건 조회
	public int addNotice(NoticeVO notice); // 글 등록
	public int modNotice(NoticeVO notice); // 글 수정
	public int remNotice(int nid); // 글 삭제
	// 댓글 등록
	// 댓글 목록
	
}
