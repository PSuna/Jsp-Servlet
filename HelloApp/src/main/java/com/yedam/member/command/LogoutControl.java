package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;

public class LogoutControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.invalidate(); // session값을 삭제
		
		try {
			resp.sendRedirect("loginForm.do"); // 다시 로그인 페이지로 이동
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
