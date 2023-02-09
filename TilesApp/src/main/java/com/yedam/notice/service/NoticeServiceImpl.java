package com.yedam.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public class NoticeServiceImpl implements NoticeService {

	SqlSession session = DataSource.getInstance().openSession(true);
	
	// session.selectOne("com.yedam.notice.mapper.NoticeMapper.getNotice")
	// NoticeService service = new NoticeServiceImpl(); 와 같은 원리인듯
	// NoticeMapper.java와 NoticeMapper.xml 을 매핑해주는 역할인듯?
	NoticeMapper mapper = session.getMapper(NoticeMapper.class);
	
	
	// 목록조회
	@Override
	public List<NoticeVO> noticeList() {
		return mapper.selectList();
	}

	// 단건조회
	@Override
	public NoticeVO getNotice(int nid) {
		mapper.increaseCnt(nid); // 조회할때마다 카운트가 증가됨
		return mapper.searchOne(nid);
	}

	// 글 등록
	@Override
	public int addNotice(NoticeVO notice) {
		return mapper.insertNotice(notice);
	}

	// 글 수정
	@Override
	public int modNotice(NoticeVO notice) {
		return mapper.updateNotice(notice);
	}

	// 글 삭제
	@Override
	public int remNotice(int nid) {
		return mapper.deleteNotice(nid);
	}

	@Override
	public List<ReplyVO> replyList(int nid) {
		return mapper.replyList(nid);
	}

	@Override
	public int removeReply(int rid) {
		return mapper.deleteReply(rid);
	}

	@Override
	public int addReply(ReplyVO reply) {
		return mapper.insertReply(reply);
	}

}
