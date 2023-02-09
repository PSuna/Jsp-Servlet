package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public interface NoticeMapper {

	public List<NoticeVO> selectList();
	public NoticeVO searchOne(int nid); // 한건조회
	public int insertNotice(NoticeVO notice); // 글등록
	public int updateNotice(NoticeVO notice); // 글수정
	public int deleteNotice(int nid); // 글삭제
	public int increaseCnt(int nid); // 조회수 증가
	// 댓글목록
	public List<ReplyVO> replyList(int nid);
	// 댓글 등록
	public int insertReply(ReplyVO reply);
	// 댓글 삭제
	public int deleteReply(int rid); // 댓글번호
	
}
