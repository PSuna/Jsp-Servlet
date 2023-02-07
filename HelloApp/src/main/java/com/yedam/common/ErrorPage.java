package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ErrorPage implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 새로고침하면 (db 수정 작업이 발생함 => sendRedirect())
		MemberVO vo = new MemberVO();
		vo.setMemberId("user1");
		vo.setMemberName("Hong");
		vo.setMemberPhone("010-1111");
		vo.setMemberAddr("대구 중구 100번지");
		
		req.setAttribute("obj", vo);
		
		EmpService service = new EmpServiceMybatis();
		req.setAttribute("empList", service.empList());
		
		
		try {
			req.getRequestDispatcher("WEB-INF/result/errorResult.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
