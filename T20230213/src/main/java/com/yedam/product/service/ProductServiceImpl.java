package com.yedam.product.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.product.mapper.ProductMapper;
import com.yedam.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	
	SqlSession session = DataSource.getInstance().openSession(true);
	
	// session.selectOne("com.yedam.notice.mapper.NoticeMapper.getNotice")
	// NoticeService service = new NoticeServiceImpl(); 와 같은 원리인듯
	// NoticeMapper.java와 NoticeMapper.xml 을 매핑해주는 역할인듯?
	ProductMapper mapper = session.getMapper(ProductMapper.class);
	
	@Override
	public List<ProductVO> productList() {
		return mapper.getList();
	}

	@Override
	public ProductVO getProduct(String productCode) {
		return mapper.getProduct(productCode);
	}

	@Override
	public List<ProductVO> relateList() {
		return mapper.relatedList();
	}

}
