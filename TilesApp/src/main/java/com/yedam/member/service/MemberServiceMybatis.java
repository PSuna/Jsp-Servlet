package com.yedam.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.mapper.MemberMapper;
import com.yedam.member.vo.MemberVO;

public class MemberServiceMybatis implements MemberService{
	
	SqlSession session = DataSource.getInstance().openSession(true); // true : 자동커밋
	
	// session.getMapper(인터페이스타입.class); 를 통해 메소드를 호출할수있다
	MemberMapper mapper = session.getMapper(MemberMapper.class); 
	
	@Override
	public MemberVO login(MemberVO member) {
		// mapper가 가지고있는 login 메소드를 호출할수있음
		// 원래는 session.selectOne("네임스페이스."); 로 사용했었는데 변경
		// 주의 ★ Mapper 인터페이스랑 Mapper xml파일이랑 id값(이름)이 동일해야함
		return mapper.login(member); 
	}

	@Override
	public int addMember(MemberVO member) {
		return mapper.addMember(member);
	}

	@Override
	public List<MemberVO> memberList() {
		return mapper.memberList();
	}

	@Override
	public MemberVO getMember(String id) {
		return mapper.getMember(id);
	}

	@Override
	public int modifyMember(MemberVO member) {
		return mapper.updateMember(member);
	}

	@Override
	public int removeMember(String mid) {
		return mapper.deleteMember(mid);
	}
	
	
}

