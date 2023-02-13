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

import com.yedam.member.command.AddMember;
import com.yedam.member.command.ImageUpload;
import com.yedam.member.command.Login;
import com.yedam.member.command.LoginForm;
import com.yedam.member.command.Logout;
import com.yedam.member.command.MemberList;
import com.yedam.member.command.MemberManager;
import com.yedam.member.command.ModifyMember;
import com.yedam.member.command.MyPageForm;
import com.yedam.member.command.RemoveMember;
import com.yedam.notice.command.AddReply;
import com.yedam.notice.command.NoticeAdd;
import com.yedam.notice.command.NoticeAddJson;
import com.yedam.notice.command.NoticeDetail;
import com.yedam.notice.command.NoticeForm;
import com.yedam.notice.command.NoticeList;
import com.yedam.notice.command.NoticeListJson;
import com.yedam.notice.command.NoticeListTable;
import com.yedam.notice.command.NoticeRemove;
import com.yedam.notice.command.RemoveReply;
import com.yedam.notice.command.ReplyList;
import com.yedam.notice.command.noticeListAjax;


public class FrontController extends HttpServlet { // 서블릿 : HttpServlet 클래스를 상속받는 .java파일 

	private Map<String, Command> map;
	private String charset = null;
	
	
	public FrontController() {
		map = new HashMap<String, Command>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		charset = config.getInitParameter("charset"); // String 타입반환 , 나중에 utf-8 설정할때 필요함
		// 예제
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm()); // 글등록화면
		map.put("/noticeAdd.do", new NoticeAdd()); // 글등록처리
		map.put("/noticeRemove.do", new NoticeRemove()); // 글삭제처리
		
		// 데이터 데이블 연습
		map.put("/noticeAddJson.do", new NoticeAddJson()); // dataTable 연습용.
		map.put("/noticeListWithTables.do", new NoticeListTable()); // 데이터 tables
		map.put("/noticeListJson.do", new NoticeListJson());
		map.put("/noticeListAjax.do", new noticeListAjax());
		
		
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
			rd.forward(req, resp); // 이동
			
		}else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
			
		}else if(viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			
			// json 데이터들을 화면에 그려서 나타냄
			// substring : 해당 길이(범위)만큼 잘라서 가져옴 => 0부터 ~ .json 부분을 자른 범위만큼 가져옴 
			resp.getWriter().print(viewPage.substring(0,viewPage.length()-5)); // 응답
			
		}
	}
	
}
