package com.yedam.product.vo;

import lombok.Data;

@Data
public class ProductVO {
	private String productCode;
	private String productName;
	private String productDesc;
	private int productPrice;
	private int salePrice;
	private double likeIt;
	private String image;
}
