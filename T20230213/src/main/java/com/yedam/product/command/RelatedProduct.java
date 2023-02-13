package com.yedam.product.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class RelatedProduct implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductService service = new ProductServiceImpl();
		
		List<ProductVO> list = service.relateList();
		
		// session : 응답하고 난뒤에도 사라지지않고 계속 유지되는 데이터임
		//HttpSession session = req.getSession(); 
					
		//session.setAttribute("relate", service.relateList()); // 로그인한 id
		//req.setAttribute("relate", list);
		
		// json 포멧 데이터 만들기 
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list); // {"id":100, "reply":"test....", , , }
		
		// {"id":100, "reply":"test....", , , }.json 를 반환
		return json + ".json"; // json포맷이라는걸 알리기위해 끝에 .json을 붙여서 보냄
	
	}

}
