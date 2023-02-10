package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;

public class RemoveMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		MemberService service = new MemberServiceMybatis();
		
		int r = service.removeMember(id);
		
		if(r > 0) {
			return "{\"retCode\" : \"Success\"}.json";
		}
		
		return "{\"retCode\" : \"Success\"}.json";
	}

}
