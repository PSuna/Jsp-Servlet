package com.yedam.emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {

	// 2. Connection 객체. db연결 쿼리실행 or 실행결과 통로
	Connection conn; // db연결
	Statement stmt = null; // 쿼리를 해석해서 실행하고 처리결과를 받아오는 객체
	PreparedStatement psmt = null; // Statement는 sql 구문처리하기 어려움 따라서 PreparedStatement 보완 => ? 사용가능
	ResultSet rs = null; // 쿼리 실행결과를 받아오는 객체 (★★ Set 컬렉션임)

	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // @ip:포트:id(오라클db)
	String user = "hr";
	String pass = "hr";

	String sql;

	public void connect() {
		// jdbc driver 가져오기 => 정상
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass); // (요청 url , id , password)
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러발생");
			e.printStackTrace();
		}
	}

	// CRUD 처리 =========================================================

	// 목록 조회
	public List<Map<String, Object>> empList() {

		sql = "select * from emp_temp";
		connect();
		List<Map<String, Object>> list = new ArrayList<>();

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Map<String, Object> map = new HashMap<>();
				map.put("emp_id", rs.getInt("employee_id"));
				map.put("first_name", rs.getString("first_name"));
				map.put("last_name", rs.getString("last_name"));
				map.put("salary", rs.getInt("salary"));
				map.put("salary", rs.getString("jab_id"));
				
				list.add(map);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	// List<Map<String, Object>> 비교
	public List<EmpVO> empVoList() {
		connect();
		sql = "select * from emp_temp order by 1"; // 첫번째 컬럼기준으로 오름차순
		List<EmpVO> list = new ArrayList<>();
		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id")); // DB에 저장된 컬럼명과 일치해야함
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				list.add(emp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// 단건조회
	public EmpVO getEmp(int id) {
		sql = "select * from emp_temp where employee_id =" + id;
		connect();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if(rs.next()) { // 가져올 데이터가(단건조회) 있으면 실행 , 단건조회는 실행결과가 1개이므로 if문만사용
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setSalary(rs.getInt("salary"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));

				return emp;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 조회된 데이터 없음
	}

	// 입력
	public int addEmp(EmpVO emp) {
		connect();
		sql = "insert into emp_temp (employee_id, last_name, email, hire_date, job_id)"
				+ "values(?, ?, ?, ?, ?)";
		int r = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId()); // 첫번째 파라미터(int)를 emp.getEmployeeId()로 채워준다는말
			psmt.setString(2, emp.getLastName());// 두번째 파라미터(String)를 emp.getLastName()로 채워준다는말
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			
			r = psmt.executeUpdate(); // 처리된 건수를 반환
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
		
	}
	
	// 수정
	public int updateEmp(int id, int sal) {
		connect();
		sql = "update emp_temp set salary = ? where employee_id = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sal);
			psmt.setInt(2, id);
			
			r = psmt.executeUpdate();
			return r;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	// 수정2 메소드 오버로딩
	public int updateEmp(EmpVO emp) {
		connect();
		sql = "update emp_temp set hire_date = ?, email = ?, job_id = ? , last_name = ? where employee_id = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getHireDate()); // hire_date
			psmt.setString(2, emp.getEmail()); // email
			psmt.setString(3, emp.getJobId()); // job_id
			psmt.setString(4, emp.getLastName()); // last_name
			psmt.setInt(5, emp.getEmployeeId()); // employee_id
			
			r = psmt.executeUpdate(); // 처리건수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r; // 0 또는 1
	}

	// 삭제
	public int deleteEmp(int id) {
		connect();
		sql = "delete from emp_temp where employee_id = ?";
		int r = 0;
		
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r; // 처리건수 반환 : 0 또는 1이상
		
	}

}
