package com.yedam.notice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVO {
	
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeSubject;
	private Date noticeDate;
	private int hitCount;
	private String attachFile;
	
}
