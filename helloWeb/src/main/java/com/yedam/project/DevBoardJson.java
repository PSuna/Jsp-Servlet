package com.yedam.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;

@WebServlet("/devBoardJson")
public class DevBoardJson extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//입력값의 한글 처리
		req.setCharacterEncoding("utf-8");
		
		// 여기서 주의할게 있음!!
		// 저장버튼을 누를시 req.getParameter("param")의 값은 null이들어감
		// 따라서 밑에 if(param.equals("update"))에서 오류가남
		// 그래서 if(param != null && param.equals("update"))로 해줘야함
		//아이디 , 개발언어 , 제목, 내용
		String id = req.getParameter("id");
		String language = req.getParameter("language");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Board board = new Board(id, language, title, content);
		BoardDAO dao = new BoardDAO();
		
		if(dao.boardInsert(board) > 0) {
			// {"retCode":"Success"}
			Board selectOneBoard = dao.boardInsertSelect(id);
			int boardNo = selectOneBoard.getBoardId();
			String boardDate = selectOneBoard.getBoardDate();
			int boardView = selectOneBoard.getBoardView();
			
			resp.getWriter().print("{\"retCode\":\"Success\"}"); // 응답 : json형식(String) 데이터 보냄
		}else {
			//{"retCode":"Fail"}
			resp.getWriter().print("{\"retCode\":\"Fail\"}"); // json(String)
		}
		
//		// param = update => DB update
//		// param = 값이 없다면 => DB insert
//		if(param != null && param.equals("update")) { // 변경버튼을 눌렀다면 실행
//			if(dao.updateEmp(vo) > 0) {
//				// {"retCode":"Success"}
//				resp.getWriter().print("{\"retCode\":\"Success\"}"); // json(String)
//			}else {
//				//{"retCode":"Fail"}
//				resp.getWriter().print("{\"retCode\":\"Fail\"}"); // json(String)
//			}
//			
//		}else { // 저장버튼을 눌렀다면 실행
//			
//			if(dao.addEmp(vo) > 0) {
//				// {"retCode":"Success"}
//				resp.getWriter().print("{\"retCode\":\"Success\"}"); // 응답 : json형식(String) 데이터 보냄
//			}else {
//				//{"retCode":"Fail"}
//				resp.getWriter().print("{\"retCode\":\"Fail\"}"); // json(String)
//			}
//		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8"); // 응답정보에 한글이 포함되어있으면 utf-8 처리
		resp.setContentType("text/json; charset=utf-8"); // json 타입이면 utf-8 처리
		
		BoardDAO dao = new BoardDAO();
		List<Board> list = dao.select();
		
		// [{"id":100, "firstName":"Hong", "email": "HONG"....},{},{}] => json(String)형태
		String json = "[";
		for(int i = 0; i < list.size(); i++) {
			json += "{\"id\":" + list.get(i).getBoardId() + 
					",\"userId\": \"" + list.get(i).getBoardUserId() +
					"\",\"language\": \"" + list.get(i).getBoardLanguage() +
					"\",\"title\": \"" + list.get(i).getBoardTitle() +
					"\",\"wDate\": \"" + list.get(i).getBoardDate().substring(0,10) +
					"\",\"content\": \"" + list.get(i).getBoardContent() +
					"\",\"view\": \"" + list.get(i).getBoardView() +
					"\"}";
			
			if(i + 1 != list.size()) { // 마지막 배열이면 ,를 안넣겠다는 말 : [{},{},{}] 이런 형태이므로
				json += ",";
			}
		}
		json += "]";
		
		resp.getWriter().print(json); // 화면에 json(String) 데이터를 그려줌
		
	}
	

}
