package com.yedam.member.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class MemberList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberService service = new MemberServiceMybatis();
		List<MemberVO> list = service.memberList();
		
		String json = "";
		Gson gson = new GsonBuilder().create();
		json = gson.toJson(list);
		
		return json + ".json";
		
	}

}
