package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	// 2. Connection 객체. db연결 쿼리실행 or 실행결과 통로
	public Connection conn; // db연결
	public Statement stmt = null; // 쿼리를 해석해서 실행하고 처리결과를 받아오는 객체
	public PreparedStatement psmt = null; // Statement는 sql 구문처리하기 어려움 따라서 PreparedStatement 보완 => ? 사용가능
	public ResultSet rs = null; // 쿼리 실행결과를 받아오는 객체 (★★ Set 컬렉션임)

	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // @ip:포트:id(오라클db)
	String user = "hr";
	String pass = "hr";

	public String sql;

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
	
	public void disconn() {
		// 사용한 리소스 환원.
		try {
			if(conn != null) { // 할당되었을때
				conn.close();
			}
			if(stmt != null) { // 할당되었을때
				stmt.close();
			}
			if(psmt != null) { // 할당되었을때
				psmt.close();
			}
			if(rs != null) { // 할당되었을때
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
