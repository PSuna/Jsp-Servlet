<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/get.jsp</title>
</head>
<body>

	<h3>form 요청방식 get/post</h3>
	<form action="../login.do" method="post"> <!-- 전송 버튼을 누르면 http://localhost:8081/HelloApp/login.do로 이동 -->
		<label>ID
			<input type="text" name="uid" value = "user1">
		</label>
		<br>
		<label>PW
			<input type="password" name="upw" value = "1234">
		</label>
		<br>
		<input type="submit" value="전송">
	</form>
	<br>
	<a href="../login.do?uid=user1&upw=1234">login페이지</a>

	<script>
		/* fetch("../login.do?uid=user1&upw=1234") // get방식 요청
		.then(resolve => resolve.text())
		.catch(reject => console.log(reject)) */
		
		fetch("../login.do", {
			method: "post", // post방식 요청
			headers: {"Content-Type" : "application/x-www-form-urlencoded"},
			body : "uid=user1&upw=1234" // 쿼리스트링 형태로 값을 넣어줌
		}) 
		.then(resolve => resolve.text())
		.catch(reject => console.log(reject))
	</script>
	
</body>
</html>