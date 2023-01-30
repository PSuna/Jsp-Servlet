package com.yedam;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;

// 서버에서 그려주는 페이지
@WebServlet("/myinfo") // /myinfo : url을 의미함
public class FirstServlet extends HttpServlet { // http 통신 요청 - 응답

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8"); // 요청 정보에 한글값이 포함되어있으면 처리해주는 메서드
		
		// 요청방식 : post 요청일 경우에 tomcat 서버가 실행해줌.
		String id = req.getParameter("emp_id"); // form태그의 name속성의 값을 읽어들일때 getParameter()메소드를 이용
		String name = req.getParameter("last_name");
		String mail = req.getParameter("email");
		String job = req.getParameter("job_id");
		String hdate = req.getParameter("hire_date");
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id)); // setEmployeeId(변수 int);
		emp.setLastName(name);
		emp.setEmail(mail); 
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		System.out.println(emp);
		
		EmpDAO dao = new EmpDAO();
		dao.addEmp(emp);
		
		doGet(req, resp); // doGet 메소드 호출
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().print("<h3>servlet page</h3>"); // client 출력 스트림
		resp.getWriter().print("<a href='info/resume.html'>이력서 </a>");
		resp.getWriter().print("<a href='index.html'>첫페이지 이동</a>");
		
		//jdbc 연결처리
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		
		resp.getWriter().print("<ul>");
		for(EmpVO emp : list) {
			resp.getWriter().print("<li>" + emp.getEmployeeId() + "/ " + emp.getLastName() + "/ " + emp.getEmail() + "</li>");
		}
		resp.getWriter().print("</ul>");
		
		
	}
	
}
