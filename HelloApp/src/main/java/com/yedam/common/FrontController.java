package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.command.LoginControl;
import com.yedam.emp.command.ServiceControl;

@WebServlet("*.do") // url
public class FrontController extends HttpServlet {

	// url 패턴과 실행할 프로그램과 매핑
	Map<String, Command> map;
	
	// 서블릿. 생명주기(규칙이 정해져있음) : 인스턴스 -> init -> service -> destroy
	// 생성자
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/service.do", new ServiceControl()); // url 패턴과 실행하고 싶은 프로그램을 넣어줌
		map.put("/login.do", new LoginControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// url패턴을 확인 => 요청페이지 어떤지?
		String uri = req.getRequestURI(); // /HelloApp/*.do : http://localhost:8081/HelloApp/service.do 에서 host 정보를 빼고 
		System.out.println("uri: " + uri);
		
		String context = req.getContextPath(); // /HelloApp : 9글자
		System.out.println("context: " + context);
		
		String page = uri.substring(context.length()); // /*.do
		System.out.println(page);
		
		Command command = map.get(page); // get(/*.do)
		command.exec(req, resp);
		
	}
	
	@Override
	public void destroy() {
		
	}
		
}
