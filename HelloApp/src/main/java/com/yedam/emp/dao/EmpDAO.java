package com.yedam.emp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.common.DAO;
import com.yedam.emp.vo.EmpVO;

public class EmpDAO extends DAO{ // DB 처리하는곳
	// 싱글톤 방식. 생성자 : private , 메소드 : public getInstance
	private static EmpDAO instance = new EmpDAO();
	
	private EmpDAO() {
		
	}
	
	public static EmpDAO getInstance() {
		return instance;
	}
	
	// 한건 조회
	public EmpVO searchEmp(int empId) {
		connect();
		sql = "select * from emp_temp where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				
				return emp;
	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			disconn();
		}
		return null;
	}
	
	
	// 한건입력
	public int insertEmp(EmpVO emp) {
		connect();
		sql = "insert into emp_temp(employee_id,last_name,email,hire_date,job_id) values (?,?,?,?,?)";
		int r = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			
			r =	psmt.executeUpdate();
			
			return r ; // 실제 처리된 건수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		return 0; 
	}
	
	// 목록조회기능
	public List<EmpVO> empList(){ 
		List<EmpVO> emps = new ArrayList<>();
		connect();
		sql = "select * from emp_temp order by employee_id";
		
		// psmt : 쿼리실행 & 실행결과를 반환.
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return emps;
	}
	
	// 직무 리스트
	public Map<String, String> jobList(){
		Map<String, String> jobs = new HashMap<>();
		connect();
		sql = "select job_id, job_title from jobs order by job_id";
		
		// psmt : 쿼리실행 & 실행결과를 반환.
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				jobs.put(rs.getString("job_id"), rs.getString("job_title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return jobs;
	}
	
	// 수정처리
	public int updateEmp(EmpVO emp) {
		connect();
		sql = "update emp_temp set first_name = ?, last_name = ?, email = ?, job_id = ?, hire_date = ? where employee_id = ?";
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getJobId());
			psmt.setString(5, emp.getHireDate());
			psmt.setInt(6, emp.getEmployeeId());
			
			int r = psmt.executeUpdate(); 
			return r; // 처리된 건수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			disconn();
		}
		return 0; // 예외 발생시 => 처리건수 0 반환
	}
	
	public int removeEmp(int id) {
		connect();
		sql = "delete from emp_temp where employee_id = ?";
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			
			int r = psmt.executeUpdate(); 
			return r; // 처리된 건수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			disconn();
		}
		return 0; // 예외 발생시 => 처리건수 0 반환
	}
}
