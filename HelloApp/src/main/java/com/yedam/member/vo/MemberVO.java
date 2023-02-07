package com.yedam.member.vo;

import lombok.Data;

@Data // lombok을 활용해서 getter , setter, toString, hashCode 등의 메소드들을 불러옴
public class MemberVO {

	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberAddr;
	private String responsibility;
	private String image;
	
}
