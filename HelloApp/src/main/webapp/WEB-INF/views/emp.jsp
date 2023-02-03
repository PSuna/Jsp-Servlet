<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<form name="myFrm" action="employee.do" method="post"> <!--  전송 버튼을 눌렀을때 /employee.do로 이동 -->
		<label>사원번호:</label>
		<input type="number" name="eid"><br>
		
		<label>LastName:</label>
		<input type="text" name="last_name"><br>
		
		<label>이메일:</label>
		<input type="email" name="email"><br>
				
		<label>입사일자:</label>
		<input type="date" name="hire_date"><br>
		
		<label>직무:</label>
		<select name="job">
			<option value="IT_PROG">개발자</option>
			<option value="SA_REP" selected>영업사원</option>
			<option value="SA_MAN">영업팀장</option>
		</select>
		<br>
		<input type="submit" value="전송">
		<input type="button" value="조회" onclick="empGetFnc()">
		<input type="button" value="다음" onclick="daumGetFnc()">
		
		<a href="../result/empList.jsp">조회</a>
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