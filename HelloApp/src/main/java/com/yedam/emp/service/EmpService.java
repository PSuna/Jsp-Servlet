package com.yedam.emp.service;

import java.util.List;

import com.yedam.vo.EmpVO;

// 업무에 대한 정의 : interface에 정의하고 구현하는 클래스를 통해 실현.
public interface EmpService {
	// 추상메소드
	public List<EmpVO> empList();
	

}
