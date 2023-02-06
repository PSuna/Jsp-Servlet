package com.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {

	// 필드
	private static SqlSessionFactory sqlSessionFactory;
	
	// 생성자
	private DataSource() {
		
	}
	
	// mabatis-config.xml 파일정보를 읽어들여서 입력스트림을 만듦
	public static SqlSessionFactory getInstance() {
		String resource = "config/mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);// xml 파일의 위치에 있는 입력스트림을 만듦
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is); // 환경파일을 읽여들여서 sessionFatory를 만들어줌
		
		return sqlSessionFactory; // sqlSessionFactory를 이용해서 sqlSession을 처리할거임
	}
	
	
}
