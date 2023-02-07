package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.service.EmpServiceMybatis;
import com.yedam.emp.vo.EmpVO;

public class EmpModControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// service : int modEmp(EmpVO) => serviceImpl : modEmp(EmpVO) -> dao : updateEmp(EmpVO)
		// id는 변경금지
		// eid=112&fname=홍도&lname=김씨
		String id = req.getParameter("eid");
		String fn = req.getParameter("fname");
		String ln = req.getParameter("lname");
		String ma = req.getParameter("email");
		String jb = req.getParameter("job");
		String hd = req.getParameter("hire");
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id));
		emp.setFirstName(fn);
		emp.setLastName(ln);
		emp.setEmail(ma);
		emp.setJobId(jb);
		emp.setHireDate(hd);
		
		EmpService service = new EmpServiceMybatis();
		
		int r = service.modEmp(emp);
		
		if(r > 0) { // 수정이 정상처리되면 목록으로 이동
			
			try {
				resp.sendRedirect("empList.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else { // 수정이 정상처리 되지않으면 에러페이지 이동
			try {
				resp.sendRedirect("errorPage.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
