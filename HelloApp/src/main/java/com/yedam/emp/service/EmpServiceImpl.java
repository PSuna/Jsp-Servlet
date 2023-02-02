package com.yedam.emp.service;

import java.util.List;

import com.yedam.emp.dao.EmpDAO;
import com.yedam.vo.EmpVO;

public class EmpServiceImpl implements EmpService{
	
	// jdbc 활용 db처리
	EmpDAO dao = EmpDAO.getInstance(); // 싱글톤 객체
	
	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}
	
}
