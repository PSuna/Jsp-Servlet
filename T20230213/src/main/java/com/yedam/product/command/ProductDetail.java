package com.yedam.product.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class ProductDetail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		
		ProductService service = new ProductServiceImpl();
		
		req.setAttribute("vo",service.getProduct(code));
		req.setAttribute("relate", service.relateList());
		
		return "product/product.tiles";
	}

}
