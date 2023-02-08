package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;

// MemberMapper.java에서 선언한 것들을(기본적인부분) 이용하여 응용 할수있는 부분
// 예를 들어 조회후 => insert 하기 등등..
public interface MemberService { // 서비스부분

	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member);
	public List<MemberVO> memberList();
	public MemberVO getMember(String id); // 회원정보 조회용
	
}
