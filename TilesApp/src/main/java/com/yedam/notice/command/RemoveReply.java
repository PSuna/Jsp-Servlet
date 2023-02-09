package com.yedam.notice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class RemoveReply implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 댓글번호 한건삭제
		String rid = req.getParameter("rid");
		
		NoticeService service = new NoticeServiceImpl();
		
		String json = "";
		
		if(service.removeReply(Integer.parseInt(rid)) > 0) {
			// {"retCode":"Success"}
			json = "{\"retCode\":\"Success\"}";
		}else {
			// {"retCode": "Fail"}
			json = "{\"retCode\": \"Fail\"}";
			
		}		
		return json + ".json";
	}

}
