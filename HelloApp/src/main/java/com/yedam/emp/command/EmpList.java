package com.yedam.emp.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;

public class EmpList implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
	
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/result/empList.jsp"); // 경로를 재지정해주는 메소드를 가지고있음
		
		try {
			rd.forward(req, resp); // 요청을 재지정함
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
