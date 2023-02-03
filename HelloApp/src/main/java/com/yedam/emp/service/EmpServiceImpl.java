package com.yedam.emp.service;

import java.util.List;

import com.yedam.emp.dao.EmpDAO;
import com.yedam.vo.EmpVO;

public class EmpServiceImpl implements EmpService{ // DB에 대한 기능을 구현해서 그것을 불러오는 역할
	
	// jdbc 활용 db처리
	EmpDAO dao = EmpDAO.getInstance(); // 싱글톤 객체
	
	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}

	@Override
	public int addEmp(EmpVO emp) {
		
		return dao.insertEmp(emp);
	}


	@Override
	public EmpVO getEmp(int empId) {
		return dao.searchEmp(empId);
	}


	
}
