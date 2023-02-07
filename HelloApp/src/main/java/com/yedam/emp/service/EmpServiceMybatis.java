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
	SqlSession session = sessionFactory.openSession(true); // true : 자동커밋 // session 객체를 하나 가져옴
	
	
	@Override
	public List<EmpVO> empList() {
		// selectList() : 리턴타입이 컬렉션임
		// selectList("네임스페이스.id값")의 쿼리를 실행하겠다는말
		// selectList  : 목록조회
		return session.selectList("com.yedam.emp.mapper.EmpMapper.empList"); 
	}

	@Override
	public int addEmp(EmpVO emp) {
		// A -> B 송금
		// session.commit();
		int r = session.insert("com.yedam.emp.mapper.EmpMapper.addEmp",emp);
		if(r>0) {
			session.commit(); // 커밋
		}else {
			session.rollback(); // 되돌아가기
		}
		return r;
	}

	@Override
	public EmpVO getEmp(int empId) {
		// selectOne : 단건조회 
		return session.selectOne("com.yedam.emp.mapper.EmpMapper.getEmp",empId);
	}

	@Override
	public Map<String, String> jobList() {
		return null; //session.select("com.yedam.emp.mapper.EmpMapper.jobList");
	}

	@Override
	public int modEmp(EmpVO emp) {
		return session.update("com.yedam.emp.mapper.EmpMapper.modEmp",emp);
	}

	@Override
	public int remove(int id) {
		return session.delete("com.yedam.emp.mapper.EmpMapper.remove",id);
	}

	
	
}
