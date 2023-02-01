package com.yedam.project;

public class Member {

	// 필드
	private int id;			// 아이디
	private String password;	// 비밀번호
	private String mail;		// 이메일
	
	// 생성자
	public Member(int id, String password, String mail) {
		this.id = id;
		this.password = password;
		this.mail = mail;
	}
	
	// 메소드
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
