package com.yedam.member.mapper;

import java.util.List;

import com.yedam.member.vo.MemberVO;

// 가장 간단한(기본적인) 부분 선언하는곳 , 예를들어 연산시 => 더하기 , 뺄셈, 나누기, 곱하기
public interface MemberMapper { // 서비스부분이 아니라 데이터를 처리해주는 곳임(DB처리)

	public MemberVO login(MemberVO member); // 로그인
	public int addMember(MemberVO member); // 등록
	public List<MemberVO> memberList(); // 회원전체목록
	public MemberVO getMember(String id); // 회원정보 조회
	public int updateMember(MemberVO member); // 회원수정
	
}
