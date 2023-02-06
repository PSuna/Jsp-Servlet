package co.edu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.book.BookVO;
import co.edu.common.BookDAO;


@WebServlet("/BookAddServlet")
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
//		BookDAO dao = new BookDAO();
//		List<BookVO> list = dao.bookList();
//		
//		Gson gson = new GsonBuilder().create();
//		response.getWriter().print(gson.toJson(list));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8"); 
		response.setContentType("text/json;charset=utf-8");
		
		BookDAO dao = new BookDAO();
		String bc = request.getParameter("bookCode");
		String nm = request.getParameter("bookName");
		String au = request.getParameter("author");
		String pre = request.getParameter("press");
		String price = request.getParameter("price");
			
		BookVO vo = new BookVO();
		vo.setBookCode(bc);
		vo.setBookName(nm);
		vo.setAuthor(au);
		vo.setPress(pre);
		vo.setPrice(Integer.parseInt(price));
		
		dao.insertBook(vo);
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(vo));
	}

}
