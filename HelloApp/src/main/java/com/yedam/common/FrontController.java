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
import com.yedam.emp.command.*;
import com.yedam.member.command.*;

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
		// 첫페이지 지정
		map.put("/main.do", new MainControl());
		map.put("/service.do", new ServiceControl()); // url 패턴과 실행하고 싶은 프로그램을 넣어줌
		map.put("/errorPage.do", new ErrorPage());
		// get : 목록출력(json) , post : 입력처리
		map.put("/employee.do", new EmpControl());
		// WEB-INF 폴더에 html 파일들이 있을때 => xxxForm.do : 페이지 오픈
		map.put("/empForm.do", new EmpForm()); //
		map.put("/empList.do", new EmpList()); // 목록페이지
		map.put("/empDetail.do", new EmpDetailControl()); // 상세페이지
		map.put("/empModForm.do", new EmpModFormControl()); // 수정 화면 페이지
		map.put("/empModify.do", new EmpModControl()); // 수정 처리 페이지
		map.put("/empRemove.do", new EmpRemoveControl()); // 삭제 처리 페이지

		// 회원관리메뉴
		map.put("/loginForm.do", new LoginFormControl()); // 로그인화면 호출
		map.put("/login.do", new LoginControl()); // 로그인 처리
		map.put("/logout.do", new LogoutControl()); // 로그아웃 처리
		map.put("/signOnForm.do", new SignOnFormControl()); // 회원가입 화면
		map.put("/signon.do", new SignOnControl()); // 회원가입
		map.put("/memberList.do", new MemberListControl()); // 회원목록

		// MyPage
		map.put("/myPageForm.do", new MyPageControl()); // 마이페이지
	}

	@Override // 모든 요청은 service 메소드를 실행함!!!!!!
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8"); // 요청에 한글정보가 포함되어있으면 utf-8로
		resp.setCharacterEncoding("utf-8"); // 응답에 한글정보가 포함되어있으면 utf-8
		resp.setContentType("text/html; charset=utf-8");

		// url패턴을 확인 => 요청페이지 어떤지?
		String uri = req.getRequestURI(); // /HelloApp/service.do : http://localhost:8081/HelloApp/service.do 에서 host
											// 정보를 빼고
//		System.out.println("uri: " + uri);

		String context = req.getContextPath(); // /HelloApp : 9글자
//		System.out.println("context: " + context);

		String page = uri.substring(context.length()); // /*.do
//		System.out.println(page);

		Command command = map.get(page); // get(/service.do)
		command.exec(req, resp);

	}

	@Override
	public void destroy() {

	}

}
