package com.yedam.notice.command;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeAddJson implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		// 글등록 처리
		String savaPath = req.getServletContext().getRealPath("/upload"); // 2) 저장경로
		int maxSize = (1024 * 1024 * 10); // 3) 최대파일사이즈 지정 : 10메가 
		String encoding = "utf-8"; // 4) 인코딩 방식
		
		// json 만들어주기위해 사용
		Gson gson = new GsonBuilder().create();
		
		NoticeVO vo = null;
		
		try {
			
			// MultipartRequest로 전송받은 데이터를 불러온다.
			// form에 enctype을 "multipart/form-data"로 선언하고 
			// submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
			MultipartRequest multi = new MultipartRequest(req,savaPath,maxSize,encoding,new DefaultFileRenamePolicy());
			
			// form에 enctype을 "multipart/form-data"로 선언하고 
			// submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
			String title = multi.getParameter("title");
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); // 사진을 여러장 업로드 할수있음
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement(); // 파일의 이름만 읽어들임
				//System.out.println(file);
				// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
				fileName = multi.getFilesystemName(file); // 동일한 파일명이 있다면 바뀐파일의 이름을 읽어오는것
			}
			
			// NoticeVO 생성
			// 여기서 notice_id는 selectkey 구문에 의해서 값이 자동적으로 넣어지므로 따로 setNoticeId를 해주지 않아도됨
			vo = new NoticeVO();
			vo.setAttachFile(fileName);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			vo.setNoticeDate(new Date()); // 날짜 : 오늘 날짜로 넣어줌
			
			NoticeService service = new NoticeServiceImpl();
			service.addNotice(vo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return gson.toJson(vo) + ".json"; // 추가할 항목을 json(String)데이터로 넘겨줌
		
		}
}
