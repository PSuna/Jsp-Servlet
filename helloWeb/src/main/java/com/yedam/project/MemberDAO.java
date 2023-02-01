package com.yedam.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MemberDAO {

	// 필드
	// 2. Connection 객체. db연결 쿼리실행 or 실행결과 통로
	Connection conn; // db연결
	Statement stmt = null; // 쿼리를 해석해서 실행하고 처리결과를 받아오는 객체
	PreparedStatement psmt = null; // Statement는 sql 구문처리하기 어려움 따라서 PreparedStatement 보완 => ? 사용가능
	ResultSet rs = null; // 쿼리 실행결과를 받아오는 객체 (★★ Set 컬렉션임)

	String url = "jdbc:oracle:thin:@192.168.0.123:1521:xe"; // @ip:포트:id(오라클db)
	String user = "dev";
	String pass = "dev";

	String sql;
	Scanner sc = new Scanner(System.in);

	
	// 메소드
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
	
	public int memberInsert(Member member) {
		connect(); // DB연결
		sql = "insert into member (user_id, user_password, user_mail) values (?, ?, ?)";
		int r = 0;
		
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, member.getId());
			psmt.setString(2,member.getPassword());
			psmt.setString(3, member.getMail());
			
			r = psmt.executeUpdate(); // 처리건수 반환
			return r;
			
		} catch (SQLException e) {
			System.out.println("━━━━━━━━━━ [이미 존재하는 ID입니다.] ━━━━━━━━━━");
			e.printStackTrace();
		}
		return r; // insert된게 없다면 0반환
	}
	
	public boolean memberLogin(String id, String password) {
		
		connect();
		sql = "select * from member where user_id = ?";
		
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
//			if(rs.next()) { // 조회된 계정이 있다면
//				if(id.equals(rs.getString("user_id")) && password.equals(rs.getString("user_password"))){ 
//					return true;
//				}else { // 아이디와 비밀번호가 틀리다면
//					System.out.println("━━━━━━━━━━ [아이디와 비밀번호가 다릅니다.] ━━━━━━━━━━");
//					System.out.print("이메일로 패스워드를 받아보시겠습니까?(Y/N)>> ");
//					String yesNo = sc.nextLine();
//					switch(yesNo) {
//						case "y":
//						case "Y":
//							Mail mail = new Mail(rs.getString("user_mail"), rs.getString("user_id"), rs.getString("user_password"));
//							mail.sendMail();
//							System.out.println("메인페이지로 이동합니다.");
//							break;
//						case "n":
//						case "N":
//							System.out.println("메인페이지로 이동합니다.");
//							break;
//					}
//					return false;
//				}
//			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("━━━━━━━━━━ [존재하지않는 계정입니다. 메인페이지로 이동합니다.] ━━━━━━━━━━");
		return false; // 조회되는 계정이 없다면
	}
	
}
