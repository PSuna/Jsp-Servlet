package com.yedam.emp.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.vo.EmpVO;

public class ServiceControl implements Command { // 구현 클래스

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("서비스컨트롤...");
		EmpService service = new EmpServiceImpl();
		List<EmpVO> list = service.empList();  
		
		// json 목록
		Gson gson = new GsonBuilder().create();
		try {
			resp.getWriter().print(gson.toJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
