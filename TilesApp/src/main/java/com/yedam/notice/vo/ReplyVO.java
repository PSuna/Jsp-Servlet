package com.yedam.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReplyVO {

	private int replyId;
	private int noticeId;
	private String replyTitle;
	private String replySubject;
	private String replyWriter;
	private Date replyDate;
	
}
