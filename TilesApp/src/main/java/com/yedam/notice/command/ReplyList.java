package com.yedam.notice.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.ReplyVO;

public class ReplyList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nid = req.getParameter("nid");
		NoticeService service = new NoticeServiceImpl();
		List<ReplyVO> list = service.replyList(Integer.parseInt(nid));
		
		// json 포멧 데이터 만들기 
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(list); // {"id":100, "reply":"test....", , , }
		
		// {"id":100, "reply":"test....", , , }.json 를 반환
		return json + ".json"; // json포맷이라는걸 알리기위해 끝에 .json을 붙여서 보냄
	}

}
