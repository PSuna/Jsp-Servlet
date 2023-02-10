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

public class MyPageForm implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(); 
		String id = (String) session.getAttribute("logId"); // 현재 로그인한 id값(세션)을 가져옴
		
		MemberService service = new MemberServiceMybatis();
		MemberVO mvo = service.getMember(id); // 현재 로그인되어있는 아이디로 단건조회
		req.setAttribute("vo", mvo); // 요청메세지에 vo를 저장 : 수정된 정보들을 다시 화면에 뿌려줄수있음
		
		return "member/mypage.tiles";
	}

}
