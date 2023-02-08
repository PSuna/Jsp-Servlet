package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class MyPageControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberService service = new MemberServiceMybatis();
		MemberVO member = service.getMember(id);
		
		req.setAttribute("vo", member);
		
		try {
			req.getRequestDispatcher("WEB-INF/member/mypage.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
