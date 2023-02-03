package com.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;

public class LoginControl implements Command { // 서버단

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		String method = req.getMethod(); // get방식인지 post인지 등을 구분해줌
		System.out.println("요청방식: " + method);
 		
		System.out.println("로그인 컨트롤");
		String id = req.getParameter("uid"); // form태그아래에 input의 name 속성을 가져옴
		String pw = req.getParameter("upw");
		System.out.println("id: " + id);
		System.out.println("pw: " + pw);
		
	}

}
