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
		}
							
	}
	
}
