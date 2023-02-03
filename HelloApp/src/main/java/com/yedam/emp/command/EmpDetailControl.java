package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.vo.EmpVO;

public class EmpDetailControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		// 상세조회 : service => serviceImpl => dao
		
		String id = req.getParameter("eid");
		
		EmpService service = new EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		// 요청 정보에다가 searchVO라는 이름으로 emp 객체의 참조주소값이 들어감
		req.setAttribute("searchVO", emp); 
		req.setAttribute("myAge", 100); 
		req.setAttribute("loginId", "user1"); 
		
		// 이전 요청에서 가져온 정보를 그대로 가져오기위해
		try {
			req.getRequestDispatcher("WEB-INF/result/empDetail.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
