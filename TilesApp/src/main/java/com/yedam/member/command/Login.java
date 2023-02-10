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
			
			// session : 응답하고 난뒤에도 사라지지않고 계속 유지되는 데이터임
			HttpSession session = req.getSession(); 
			
			session.setAttribute("logId", rvo.getMemberId()); // 로그인한 id
			session.setAttribute("logName", rvo.getMemberName()); // 로그인한 이름
			session.setAttribute("Auth", rvo.getResponsibility()); // 권한
			
			req.setAttribute("vo", rvo); // 요청정보에 vo를 저장 해당 데이터를 가지고 => 마이페이지로 이동
			
			return "member/mypage.tiles";
			
		}else { 
			req.setAttribute("result", "회원정보를 확인하세요!!"); // 요청 정보 : 응답하고 나면 사라짐
			return "member/login.tiles";
		}
		
	}

}
