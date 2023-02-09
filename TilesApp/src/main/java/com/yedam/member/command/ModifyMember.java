package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ModifyMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("mid");
		String name = req.getParameter("mname");
		String pass = req.getParameter("mpass");
		String phone = req.getParameter("mphone");
		String addr = req.getParameter("maddr");
		//String image = req.getParameter("");
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberName(name);
		member.setMemberPw(pass);
		member.setMemberPhone(phone);
		member.setMemberAddr(addr);
		
		MemberService service = new MemberServiceMybatis();
		int r = service.modifyMember(member);
		
		if(r > 0) {
			return "noticeList.do";
		}
		
		return "member/mypage.tiles";
	}

}
