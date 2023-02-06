package com.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.emp.vo.EmpVO;

// EmpServiceImpl : jdbc
// EmpSerbiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService {

	SqlSessionFactory sessionFactory = DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(); // session 객체를 하나 가져옴
	
	
	@Override
	public List<EmpVO> empList() {
		// selectList() : 리턴타입이 컬렉션임
		// selectList("네임스페이스.id값")의 쿼리를 실행하겠다는말
		// selectList  : 목록조회
		return session.selectList("com.yedam.emp.mapper.EmpMapper.empList"); 
	}

	@Override
	public int addEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EmpVO getEmp(int empId) {
		// selectOne : 단건조회 
		return session.selectOne("com.yedam.emp.mapper.EmpMapper.getEmp",empId);
	}

	@Override
	public Map<String, String> jobList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
