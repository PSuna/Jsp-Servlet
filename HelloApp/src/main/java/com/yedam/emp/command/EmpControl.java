package com.yedam.emp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.vo.EmpVO;

public class EmpControl implements Command { // 서버단

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		RequestDispatcher rd = null;
		
		String method = req.getMethod();
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(method.equals("GET")) { // GET방식 : 목록조회
			
			try {
				//resp.sendRedirect("result/empList.jsp");
				resp.sendRedirect("https://www.daum.net/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equals("POST")) { // POST방식 : 입력처리
			// 입력하기 위한 처리
			// form에서 작성한 input태그의 name속성을 넣어줌
			String eid = req.getParameter("eid");
			String lName = req.getParameter("last_name");
			String mail = req.getParameter("email");
			String hire = req.getParameter("hire_date");
			String job = req.getParameter("job");
			
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid));
			emp.setLastName(lName);
			emp.setJobId(job);
			emp.setHireDate(hire);
			emp.setEmail(mail);
			
			// 서비스 로직
			EmpService service = new EmpServiceImpl();
			int r = service.addEmp(emp);
			
			// 요청재지정 : 다른페이지를 그려줄수있음(나타낼수있음)
			try {
				if( r > 0) {
					resp.sendRedirect("empList.do");
					//rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
					//rd.forward(req, resp); // 페이지 재지정
				}else {
					//resp.sendRedirect("WEB-INF/result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req, resp); // 페이지 재지정
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}else {
			
		}
		
	}

	
	
}
