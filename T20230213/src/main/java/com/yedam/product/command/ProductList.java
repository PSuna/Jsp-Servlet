package com.yedam.product.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;
import com.yedam.product.vo.ProductVO;

public class ProductList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductService service = new ProductServiceImpl();
		
		req.setAttribute("list", service.productList());
		
		return "product/productList.tiles";
	}

}
