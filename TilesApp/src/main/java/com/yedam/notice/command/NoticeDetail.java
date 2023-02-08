package com.yedam.notice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeDetail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nid = req.getParameter("nid");
		
		NoticeService service = new NoticeServiceImpl();

		req.setAttribute("vo",service.getNotice(Integer.parseInt(nid)));
		
		return "notice/noticeDetail.tiles";
	}

}
