package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.vo.EmpVO;

public class EmpModFormControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 수정 화면 : WEB-INF/views/modify.jsp
		// 수정 버튼을 누르면 id 값을 받아서
		// WEB-INF/views/modify.jsp가 화면에 나타나도록 구현
		
		String id = req.getParameter("id");
		
		EmpService service = new EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		// 요청 정보에다가 searchVO라는 이름으로 emp 객체의 참조주소값이 들어감
		req.setAttribute("searchVO", emp); 
		

		try {
			req.getRequestDispatcher("WEB-INF/views/modify.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
