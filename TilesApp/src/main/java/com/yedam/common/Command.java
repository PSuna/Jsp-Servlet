package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command{

	// exec 메소드를 호출하는 영역으로 ServletException, IOException 예외를 넘겨줌
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
}
