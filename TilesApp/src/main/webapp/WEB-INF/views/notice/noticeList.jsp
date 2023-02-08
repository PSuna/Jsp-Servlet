<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<style>

	.center{
		text-align: center;
	}
	
</style>

    <table class="table">
    	<thead>
    		<tr>
    			<th>글번호</th>
    			<th>작성자</th>
    			<th>제목</th>
    			<th>조회수</th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach var="notice" items="${list}">
    		<tr>
    			<td>
    				<a href="noticeDetail.do?nid=${notice.noticeId}">${notice.noticeId}</a>
    			</td>
    			<td>${notice.noticeWriter}</td>
    			<td>${notice.noticeTitle}</td>
    			<td>${notice.hitCount}</td>
    		</tr>
    		</c:forEach>
    	</tbody>
    </table>
    
<div class="center">
	<button id="addBtn">게시글등록</button>
</div>

<script>
	document.getElementById("addBtn").addEventListener("click", function(){
		location.href = "noticeForm.do";
	})
</script>
