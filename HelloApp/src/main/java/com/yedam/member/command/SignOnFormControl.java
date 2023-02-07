package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;

public class SignOnFormControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// signon.jsp
		
		try {
			req.getRequestDispatcher("WEB-INF/member/signon.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
