package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;

public class EmpRemoveControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		
		EmpService service = new EmpServiceImpl();
		int r = service.remove(Integer.parseInt(id));
		
		if(r > 0) { // 삭제가 정상처리 되면 리스트목록이동
			try {
				resp.sendRedirect("empList.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else { // 삭제가 정상처리 되지않으면 에러페이지 이동
			try {
				resp.sendRedirect("errorPage.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
