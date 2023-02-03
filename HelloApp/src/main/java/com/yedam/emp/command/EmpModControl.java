package com.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.vo.EmpVO;

public class EmpModControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// service : int modEmp(EmpVO) => serviceImpl : modEmp(EmpVO) -> dao : updateEmp(EmpVO)
		// id는 변경금지
		
	
		
		
	}

}
