package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.command.*;
import com.yedam.notice.command.*;


public class FrontController extends HttpServlet {

	private Map<String, Command> map;
	private String charset = null;
	
	
	public FrontController() {
		map = new HashMap<String, Command>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		charset = config.getInitParameter("charset"); // String 타입반환 , 나중에 utf-8 설정할때 필요함
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm()); // 글등록화면
		map.put("/noticeAdd.do", new NoticeAdd()); // 글등록처리
		
		// 댓글
		map.put("/replyList.do", new ReplyList()); // 댓글 목록
		map.put("/removeReply.do", new RemoveReply()); // 댓글 삭제
		map.put("/addReply.do", new AddReply()); // 댓글 등록
		
		// 회원관련
		map.put("/loginForm.do", new LoginForm()); // 로그인화면
		map.put("/login.do", new Login()); // 로그인 처리
		map.put("/logout.do", new Logout()); // 로그아웃
		map.put("/modifyMember.do", new ModifyMember()); // 수정 페이지
		map.put("/myPageForm.do", new MyPageForm()); // 마이페이지
		map.put("/imageUpload.do", new ImageUpload()); // 이미지 변경 페이지
		
		// 관리자 회원관리
		map.put("/memberManageForm.do", new MemberManager());
		map.put("/memberList.do", new MemberList());
		map.put("/addMember.do", new AddMember());
		map.put("/removeMember.do", new RemoveMember()); 
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset); // utf-8 설정
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command command = map.get(page);
		
		// main/main.tiles 등의 값이 넘어옴
		// notice/noticeList.tiles 등의 값이 넘어옴
		String viewPage = command.exec(req, resp); 
		
		
		if(viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
			
		}else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
			
		}else if(viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0,viewPage.length()-5)); // json데이터들을 화면에 뿌려준다.
		}
	}
	
}
