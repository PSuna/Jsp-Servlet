package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class SignOnControl implements Command{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		// form:multipart/form-data => 처리(MultipartRequest)
		// cos 와 commons fileupload 라이브러리가 필요함
		// 생성자 매개값 : 1) 요청정보, 2) 저장경로, 3) 최대파일사이즈 지정, 4) 인코딩, 5) rename정책(똑같은 이름의 이미지가 있는경우)
		
		
		String savaPath = req.getServletContext().getRealPath("/images"); // 2) 저장경로
		int maxSize = (1024 * 1024 * 10); // 3) 최대파일사이즈 지정 : 10메가 
		String encoding = "utf-8"; // 4) 인코딩 방식
		
		try {
			// 파일업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req,savaPath,maxSize,encoding,new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("member_id");
			String pw = multi.getParameter("member_pw");
			String nm = multi.getParameter("member_name");
			String ph = multi.getParameter("member_phone");
			
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); // 사진을 여러장 업로드 할수있음
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement(); // 파일의 이름만 읽어들임
				System.out.println(file);
				fileName = multi.getFilesystemName(file); // 동일한 파일명이 있다면 바뀐파일의 이름을 읽어오는것
			}
			
			MemberVO member = new MemberVO();
			member.setMemberId(id);
			member.setMemberPw(pw);
			member.setMemberName(nm);
			member.setMemberPhone(ph);
			member.setImage(fileName);
			
			MemberService service = new MemberServiceMybatis();
			if(service.addMember(member) > 0) {
				resp.sendRedirect("empList.do");
			}else {
				resp.sendRedirect("errorPage.do");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	
}
