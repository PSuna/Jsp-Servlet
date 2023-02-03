<%@page import="com.yedam.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
	//리턴되는 타입은 객체타입
	// 아까 컨트롤러에서 넘겨준 요청정보에 searchVO가 참조하고있는 주소값을 가져옴
	EmpVO emp = (EmpVO)request.getAttribute("searchVO"); 
%>

<h3>현재 페이지는 empModForm.do의 결과 modify.jsp입니다.</h3>
<table class="table">
	<tr>
		<th>사원번호</th><td><input readOnly type="text" value="<%=emp.getEmployeeId()%>"></td>
	</tr>
	<tr>
		<th>FirstName</th><td><input type="text" value="<%=emp.getFirstName()%>"></td>
	</tr>
	<tr>
		<th>LastName</th><td><input type="text" value="<%=emp.getLastName()%>"></td>
	</tr>
	<tr>
		<th>이메일</th><td><input type="text" value="<%=emp.getEmail()%>"></td>
	</tr>
	<tr> 
		<th>직무</th>
		<td>
			<select name="job"> <!-- emp.getJobId() -->
				<option value="IT_PROG" selected>개발자</option>
				<option value="SA_REP" selected>영업사원</option>
				<option value="SA_MAN" selected>영업팀장</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>입사일자</th><td><input type="text" value="<%=emp.getHireDate()%>"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button class="btn btn-primary" onclick="location.href='empDetail.do'">변경</button>
		</td>
	</tr>
</table>


<jsp:include page="../includes/footer.jsp"></jsp:include>