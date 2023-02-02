package com.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command { 

	// 추상 메소드
	// HttpServletRequest req : 클라이언트측에서 보내는 요청정보 
	// HttpServletResponse resp : 응답정보
	public void exec(HttpServletRequest req, HttpServletResponse resp);
	
}
