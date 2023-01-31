package com.yedam.emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empListJson")
public class EmpListJson extends HttpServlet {

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//입력값의 한글 처리
//		req.setCharacterEncoding("utf-8");
//		
//		String id = req.getParameter("id");
//		String name = req.getParameter("name");
//		String job = req.getParameter("job");
//		String hire = req.getParameter("hire");
//		String mail = req.getParameter("mail");
//		
//		EmpVO vo = new EmpVO();
//		vo.setEmployeeId(Integer.parseInt(id));
//		vo.setLastName(name);
//		vo.setJobId(job);
//		vo.setHireDate(hire);
//		vo.setEmail(mail);
//		
//		System.out.println(vo);
//		resp.getWriter().print("complete");
//		
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//입력값의 한글 처리
		req.setCharacterEncoding("utf-8");
		
		// 여기서 주의할게 있음!!
		// 저장버튼을 누를시 req.getParameter("param")의 값은 null이들어감
		// 따라서 밑에 if(param.equals("update"))에서 오류가남
		// 그래서 if(param != null && param.equals("update"))로 해줘야함
		String param = req.getParameter("param");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String job = req.getParameter("job");
		String hire = req.getParameter("hire");
		String mail = req.getParameter("mail");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setJobId(job);
		vo.setHireDate(hire);
		vo.setEmail(mail);
		
		EmpDAO dao = new EmpDAO();
		
		// param = update => DB update
		// param = 값이 없다면 => DB insert
		if(param != null && param.equals("update")) { // 변경버튼을 눌렀다면 실행
			if(dao.updateEmp(vo) > 0) {
				// {"retCode":"Success"}
				resp.getWriter().print("{\"retCode\":\"Success\"}"); // json(String)
			}else {
				//{"retCode":"Fail"}
				resp.getWriter().print("{\"retCode\":\"Fail\"}"); // json(String)
			}
			
		}else { // 저장버튼을 눌렀다면 실행
			
			if(dao.addEmp(vo) > 0) {
				// {"retCode":"Success"}
				resp.getWriter().print("{\"retCode\":\"Success\"}"); // 응답 : json형식(String) 데이터 보냄
			}else {
				//{"retCode":"Fail"}
				resp.getWriter().print("{\"retCode\":\"Fail\"}"); // json(String)
			}
		}
	}
	
	// 제어의 역전(IOC)
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 처리의 주체 : 톰캣
		String id = req.getParameter("del_id"); // 요청페이지에서 del_id로 파라미터 지정
		
		EmpDAO dao = new EmpDAO();
		if(dao.deleteEmp(Integer.parseInt(id)) > 0) { // 삭제건수가 1이상이면
			// {"retCode":"Success"}
			resp.getWriter().print("{\"retCode\":\"Success\"}"); // json(String)
		}else {
			//{"retCode":"Fail"}
			resp.getWriter().print("{\"retCode\":\"Fail\"}"); // json(String)
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8"); // 응답정보에 한글이 포함되어있으면 utf-8 처리
		resp.setContentType("text/json; charset=utf-8"); // json 타입이면 utf-8 처리
		
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		
		// [{"id":100, "firstName":"Hong", "email": "HONG"....},{},{}] => json(String)형태
		String json = "[";
		for(int i = 0; i < list.size(); i++) {
			json += "{\"id\":" + list.get(i).getEmployeeId() + 
					",\"firstName\": \"" + list.get(i).getFirstName() +
					"\",\"lastName\": \"" + list.get(i).getLastName() +
					"\",\"email\": \"" + list.get(i).getEmail() +
					"\",\"hireDate\": \"" + list.get(i).getHireDate().substring(0,10) +
					"\",\"job\": \"" + list.get(i).getJobId() +
					"\"}";
			
			if(i + 1 != list.size()) { // 마지막 배열이면 ,를 안넣겠다는 말 : [{},{},{}] 이런 형태이므로
				json += ",";
			}
		}
		json += "]";
		
		resp.getWriter().print(json); // 화면에 json(String) 데이터를 그려줌
		
	}
}
