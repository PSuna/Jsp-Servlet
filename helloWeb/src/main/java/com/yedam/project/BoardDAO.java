package com.yedam.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardDAO {

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
	
	public int updateBoard(int updateNo, String changeItem, String change) {
		connect();
		int r = 0;
		switch(changeItem) {
			case "board_title":
				sql = "update board set board_title = ? where board_id = ?";
				break;
			case "board_language":
				sql = "update board set board_language = ? where board_id = ?";
				break;
			case "board_content":
				sql = "update board set board_content = ? where board_id = ?";
				break;
		}
		
		try {
			psmt = conn.prepareStatement(sql);
			
			//psmt.setString(1, changeItem);
			psmt.setString(1, change);
			psmt.setInt(2, updateNo);
			
			r = psmt.executeUpdate(); // 처리건수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}
	
	public int deleteBoard(int deleteNo) {
		connect();
		int r = 0;
		sql = "delete from board where board_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, deleteNo);
			r = psmt.executeUpdate(); // 처리건수 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
		
	}
	
	public Board selectOne(int boardNo) {
		Board board = new Board();
		connect();

		try {
			// 조회수 증가후
			sql = "update board set board_view = board_view + 1 where board_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate();
			
			// 단건조회
			sql = "select * from board where board_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery(); // 처리결과 반환
			
			if(rs.next()) {
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardLanguage(rs.getString("board_language"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardUserId(rs.getString("board_user_id"));
				board.setBoardView(rs.getInt("board_view"));
				board.setBoardContent(rs.getString("board_content"));
				
				return board;
			}
			
		} catch (SQLException e) {
			System.out.println("단건조회 처리중 에러!!");
			e.printStackTrace();
		}
		return null; //조회된 데이터 없음
	}
	
	public List<Board> select() {
		List<Board> list = new ArrayList<>();
		connect();
		sql = "select * from board order by board_id";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // 처리결과 반환
			
			while(rs.next()) {
				//int boardId, String boardTitle, String boardUserId, String boardDate, int boardView, String title, String content, String boardLanguage
				list.add(new Board(rs.getInt("board_id"), rs.getString("board_title"), rs.getString("board_user_id"), rs.getString("board_date"), rs.getInt("board_view"), rs.getString("board_title"), rs.getString("board_content")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Board> select(String loginUserId) {
		List<Board> list = new ArrayList<>();
		connect();
		sql = "select * from board where board_user_id = ? order by board_id";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginUserId);
			rs = psmt.executeQuery(); // 처리결과 반환
			
			while(rs.next()) {
				list.add(new Board(rs.getInt("board_id"), rs.getString("board_title"), rs.getString("board_user_id"), rs.getString("board_date"), rs.getInt("board_view")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int boardInsert(Board board) {
		connect(); // DB연결
		sql = "insert into board (board_id, board_user_id, board_language, board_title, board_content) values (board_seq.NEXTVAL, ?, ?, ?, ?)";
		int r = 0;
		
		try {
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardUserId());
			psmt.setString(2, board.getBoardLanguage());
			psmt.setString(3, board.getBoardTitle());
			psmt.setString(4, board.getBoardContent());
			
			r = psmt.executeUpdate(); // 처리건수 반환
			return r;
			
		} catch (SQLException e) {
			System.out.println("처리중 에러!!");
			e.printStackTrace();
		}
		return r; // insert된게 없다면 0반환
	}
	
	public Board boardInsertSelect(String userId) {
		Board board = new Board();
		sql = "select * from board where board_user_id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery(); // 처리결과 반환
			
			if(rs.next()) {
				
				board.setBoardId(rs.getInt("board_id"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardLanguage(rs.getString("board_language"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardUserId(rs.getString("board_user_id"));
				board.setBoardView(rs.getInt("board_view"));
				board.setBoardContent(rs.getString("board_content"));
				
				return board;
		}
		
	} catch (SQLException e) {
		System.out.println("단건조회 처리중 에러!!");
		e.printStackTrace();
	}
		return null; //조회된 데이터 없음
	}
	
}
