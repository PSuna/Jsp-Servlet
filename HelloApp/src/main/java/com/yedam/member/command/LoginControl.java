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

public class LoginControl implements Command { // 서버단

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		// 로그인이 되면 session객체 setAttribute('id')
		
		String method = req.getMethod(); // get방식인지 post인지 등을 구분해줌
		System.out.println("요청방식: " + method);
 		
		System.out.println("로그인 컨트롤");
		String id = req.getParameter("mid"); // form태그아래에 input의 name 속성을 가져옴
		String pw = req.getParameter("mpw");
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		MemberService service = new MemberServiceMybatis();
		MemberVO rvo = service.login(member);
		
		if(rvo != null) { // rvo가 있다면(로그인이 정상처리되면)
			try {
				resp.sendRedirect("empList.do"); // 이동
				HttpSession session = req.getSession(); // 쿠키정보를 가져옴
				
				session.setAttribute("id", rvo.getMemberId());
				session.setAttribute("name", rvo.getMemberName());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else { //rvo가 없다면(로그인 정보가 없으면)	
			try {
				req.setAttribute("result", "회원정보를 확인하세요!!");
				req.getRequestDispatcher("WEB-INF/member/login.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
