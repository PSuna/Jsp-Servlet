package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainControl implements Command{

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "main/main.tiles"; // web.xml에 *.tiles 요청이 들어오면 tiles가 알아서 실행해줌
	}

}
