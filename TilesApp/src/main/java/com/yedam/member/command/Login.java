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

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 성공하면 mypage로 이동하고
		// 로그인 실패하면 다시 로그인화면으로 이동할때 "아이디와 패스워드를 확인"하도록
		
		String id = req.getParameter("mid");
		String pass = req.getParameter("mpw");
		
		MemberService service = new MemberServiceMybatis();
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pass);
		
		MemberVO rvo = service.login(member);
		if( rvo != null) {
			HttpSession session = req.getSession(); // 쿠키정보를 가져옴
			
			session.setAttribute("logId", rvo.getMemberId());
			session.setAttribute("logPass", rvo.getMemberPw());
			session.setAttribute("logName", rvo.getMemberName());
			
			req.setAttribute("vo", rvo); // 세션에 저장
			return "member/mypage.tiles";
			
		}else { 
			req.setAttribute("result", "회원정보를 확인하세요!!");
			return "member/login.tiles";
		}
		
	}

}
