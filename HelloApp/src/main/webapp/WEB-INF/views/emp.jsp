<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<%
		Map<String,String> list = (Map<String,String>)request.getAttribute("jobList");
	%>
	
	<form name="myFrm" action="employee.do" method="post"> <!--  전송 버튼을 눌렀을때 /employee.do로 이동 -->
		<table class="table">
		<tr>
			<th><label>사원번호:</label></th>
			<td><input type="number" name="eid"></td>
		</tr>
		
		<tr>
			<th><label>LastName:</label></th>
			<td><input type="text" name="last_name"></td>
		</tr>
		
		
		<tr>
			<th><label>이메일:</label></th>
			<td><input type="email" name="email"></td>
		</tr>
			
		<tr>	
			<th><label>입사일자:</label></th>
			<td><input type="date" name="hire_date"></td>
		</tr>
		
		<tr>
			<th><label>직무:</label></th>
		<td><select name="job">
			<%
				for(Entry<String,String> ent : list.entrySet()){
			%>
					<option value="<%=ent.getKey()%>"><%=ent.getValue()%></option>
			<%}%>
		
		</select></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="전송">
			<input type="button" value="조회" onclick="empGetFnc()">
			<input type="button" value="다음" onclick="daumGetFnc()"></td>
		<tr>
		<!-- <a href="../result/empList.jsp">조회</a> -->
		</table>
	</form>

	<script>
	
		console.log(this); // 얘는 Window 객체를 뜻함
		console.log(document.forms.myFrm); // 폼이름 myFrm인것을 찾아줌
		function empGetFnc(){
			document.forms.myFrm.method = "get"; // get방식으로 바꿈
			document.forms.myFrm.action = "empList.do";
			document.forms.myFrm.submit(); // submit : get방식으로 전송함
		}
		
		function daumGetFnc(){
			document.forms.myFrm.method = "get"; // delete방식으로 바꿈
			document.forms.myFrm.submit(); // submit : delete방식으로 전송함
		}
	</script>
	<jsp:include page="../includes/footer.jsp"></jsp:include>