package com.yedam.product.service;

import java.util.List;

import com.yedam.product.vo.ProductVO;

public interface ProductService {
	// 목록, 단건조회.
	public List<ProductVO> productList();
	public ProductVO getProduct(String productCode);
	public List<ProductVO> relateList();
}
