<%@page import="com.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
	// 리턴되는 타입은 객체타입
	// 아까 컨트롤러에서 넘겨준 요청정보에 searchVO가 참조하고있는 주소값을 가져옴
	EmpVO emp = (EmpVO)request.getAttribute("searchVO");
%>

	<h3>현재 페이지는 empModForm.do의 결과 modify.jsp입니다.</h3>
	<form action="./empModify.do" method="post"> <!-- post 방식으로 보냄 -->
		<table class="table">
			<tr>
				<th>사원번호</th>
				<td><input readOnly type="text" name="eid" value="<%=emp.getEmployeeId()%>"></td>
			</tr>
			<tr>
				<th>FirstName</th>
				<td><input type="text"  name="fname" value="<%=emp.getFirstName()%>"></td>
			</tr>
			<tr>
				<th>LastName</th>
				<td><input type="text" name="lname" value="<%=emp.getLastName()%>"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" value="<%=emp.getEmail()%>"></td>
			</tr>
			<tr> 
				<th>직무</th>
				<td><input name = "job" type="text" value="<%=emp.getJobId()%>"></td>
			</tr>
			<tr>
				<th>입사일자</th>
				<td><input name="hire" type="text" value="<%=emp.getHireDate().substring(0,10)%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button class="btn btn-primary" onclick="location.href='empDetail.do'">변경</button>
				</td>
			</tr>
		</table>
</form>


<jsp:include page="../includes/footer.jsp"></jsp:include>