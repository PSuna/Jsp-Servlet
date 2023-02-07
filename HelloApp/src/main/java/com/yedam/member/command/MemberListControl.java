package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;

public class MemberListControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		MemberService service = new MemberServiceMybatis();
		
		req.setAttribute("memberList", service.memberList());
		
		try {
			req.getRequestDispatcher("WEB-INF/member/memberList.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
