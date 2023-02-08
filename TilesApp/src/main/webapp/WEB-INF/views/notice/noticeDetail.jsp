<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 날짜의 format(형식)을 줄수있음 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<table class="table">
	<tr>
		<th>글번호</th>
		<td>${vo.noticeId}</td>
		
		<th>조회수</th>
		<td>${vo.hitCount}</td>
	</tr>
	
	<tr>
		<th colspan="2">작성자</th>
		<td colspan="2">${vo.noticeWriter}</td>
	</tr>
	
	<tr>
		<th colspan="2">제목</th>
		<td colspan="2">${vo.noticeTitle}</td>
	</tr>
	
	<tr>
		<th colspan="2">내용</th>
		<td colspan="3"><textarea cols="60" rows="4">${vo.noticeSubject}</textarea></td>
	</tr>

	<tr>
		<th colspan="2">작성일자</th>
		<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${vo.noticeDate}"/></td>
		
		<th>첨부파일</th>
		<td><a target="_blank" href="upload/${vo.attachFile}">${vo.attachFile}</a></td>
	</tr>
	
</table>

