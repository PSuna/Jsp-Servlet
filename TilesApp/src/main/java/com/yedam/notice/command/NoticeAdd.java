package com.yedam.notice.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeAdd implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		// 글등록 처리
		String savaPath = req.getServletContext().getRealPath("/upload"); // 2) 저장경로
		int maxSize = (1024 * 1024 * 10); // 3) 최대파일사이즈 지정 : 10메가 
		String encoding = "utf-8"; // 4) 인코딩 방식
		
		try {
			// 파일업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req,savaPath,maxSize,encoding,new DefaultFileRenamePolicy());
			
			String title = multi.getParameter("title");
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); // 사진을 여러장 업로드 할수있음
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement(); // 파일의 이름만 읽어들임
				//System.out.println(file);
				fileName = multi.getFilesystemName(file); // 동일한 파일명이 있다면 바뀐파일의 이름을 읽어오는것
			}
			
			// NoticeVO 생성
			NoticeVO vo = new NoticeVO();
			vo.setAttachFile(fileName);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			
			
			NoticeService service = new NoticeServiceImpl();
			service.addNotice(vo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "noticeList.do";
		
		}
}
