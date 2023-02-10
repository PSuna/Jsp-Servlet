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
		
		String param = req.getParameter("param");
		String id = req.getParameter("mid");
		String name = req.getParameter("mname");
		String pass = req.getParameter("mpass");
		String phone = req.getParameter("mphone");
		String addr = req.getParameter("maddr");
		String auth = req.getParameter("resp");
		
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberName(name);
		member.setMemberPw(pass);
		member.setMemberPhone(phone);
		member.setMemberAddr(addr);
		member.setResponsibility(auth);
		
		MemberService service = new MemberServiceMybatis();
		int r = service.modifyMember(member);
		
		
		if(param != null && param.equals("ajax")) { // 회원관리에서 수정
			
			if(r > 0) {
				return "{\"retCode\" : \"Success\"}.json";
			}else {
				return "{\"retCode\" : \"Fail\"}.json";
			}
			
		} else { // 마이페이지에서 수정
			if(r > 0) { // 업데이트 성공시 noticeList.do로 이동
				return "noticeList.do";
			}else {
				return "member/mypage.tiles"; // 실패시 mypage에 머물러있기
			}
		}
	}

}
