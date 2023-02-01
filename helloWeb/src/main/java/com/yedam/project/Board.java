package com.yedam.project;

public class Board {
	// 필드
	private int boardId;
	private String boardUserId;
	private String boardLanguage;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private int boardView;
	
	// 생성자
	public Board() {
		
	}
	
	public Board(String boardUserId, String boardLanguage, String boardTitle, String boardContent) {
		this.boardUserId = boardUserId;
		this.boardLanguage = boardLanguage;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}
	
	public Board(int boardId, String boardTitle, String boardUserId, String boardDate, int boardView) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardUserId = boardUserId;
		this.boardDate = boardDate;
		this.boardView = boardView;
	}
	
	public Board(int boardId, String boardTitle, String boardUserId, String boardDate, int boardView, String boardContent, String boardLanguage) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardUserId = boardUserId;
		this.boardDate = boardDate;
		this.boardView = boardView;
		this.boardContent = boardContent;
		this.boardLanguage = boardLanguage;
	}

	// 메소드
	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardView() {
		return boardView;
	}


	public void setBoardView(int boardView) {
		this.boardView = boardView;
	}
	
	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	public String getBoardUserId() {
		return boardUserId;
	}

	public void setBoardUserId(String boardUserId) {
		this.boardUserId = boardUserId;
	}

	public String getBoardLanguage() {
		return boardLanguage;
	}

	public void setBoardLanguage(String boardLanguage) {
		this.boardLanguage = boardLanguage;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

}
