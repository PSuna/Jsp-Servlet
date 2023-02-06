<%@page import="com.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
	//리턴되는 타입은 객체타입
	// 아까 컨트롤러에서 넘겨준 요청정보에 searchVO가 참조하고있는 주소값을 가져옴
	EmpVO emp = (EmpVO)request.getAttribute("searchVO"); 
	Integer age = (Integer)request.getAttribute("myAge");
	String id = (String)request.getAttribute("loginId");
%>

<!--  <%=age %>, <%=id %> -->

<h3>현재 페이지는 empDatail.do의 결과 empDetail.jsp입니다.</h3>
<table class="table">
	<tr>
		<th>사원번호</th><td><%=emp.getEmployeeId()%></td>
	</tr>
	<tr>
		<th>FirstName</th><td><%=emp.getFirstName()%></td>
	</tr>
	<tr>
		<th>LastName</th><td><%=emp.getLastName()%></td>
	</tr>
	<tr>
		<th>이메일</th><td><%=emp.getEmail()%></td>
	</tr>
	<tr>
		<th>직무</th><td><%=emp.getJobId()%></td>
	</tr>
	<tr>
		<th>입사일자</th><td><%=emp.getHireDate()%></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button class="btn btn-primary" onclick="location.href='empModForm.do?id=<%=emp.getEmployeeId()%>'">수정</button>
			<button class="btn btn-warning" onclick="location.href='empRemove.do?id=<%=emp.getEmployeeId()%>'">삭제</button> <!-- empRemove.do?id=? / removeEmp(int id) / 삭제후 목록으로 -->
		</td>
	</tr>
</table>


<jsp:include page="../includes/footer.jsp"></jsp:include>