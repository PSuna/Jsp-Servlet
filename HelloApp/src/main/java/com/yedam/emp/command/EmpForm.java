package com.yedam.emp.command;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.service.EmpServiceMybatis;

public class EmpForm implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/emp.jsp"); // 경로를 재지정해주는 메소드를 가지고있음
		
		//EmpService service = new EmpServiceMybatis();
		EmpService service = new EmpServiceImpl();
		Map<String, String> jobList = service.jobList();
		
		
		req.setAttribute("jobList", jobList);
		
		try {
			rd.forward(req, resp); // 요청을 재지정함
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
