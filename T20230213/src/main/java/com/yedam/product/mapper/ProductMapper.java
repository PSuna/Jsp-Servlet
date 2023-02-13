package com.yedam.product.mapper;

import java.util.List;

import com.yedam.product.vo.ProductVO;

public interface ProductMapper {
	// 목록, 단건조회.
	public List<ProductVO> getList();
	public ProductVO getProduct(String productCode);
	public List<ProductVO> relatedList();
}
