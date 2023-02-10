<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jQuery : CDN방식으로 추가함 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<h3>현재 페이지는 myPageForm.do의 결과 mypage.jsp입니다.</h3>

<form action="modifyMember.do" method="post">

<!-- 이미지파일 업로드 -->
<input type="file" id="fileUpload" accept="image/*" style="display:none" onchange="imageChangeFnc()">

	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="mid" value="${vo.memberId}" readonly></td> 
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="mname" value="${vo.memberName}"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="mpass" value="${vo.memberPw}"></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="mphone" value="${vo.memberPhone}"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="maddr" value="${vo.memberAddr}"></td>
		</tr>
		 <tr>
			<th>image</th>
			<td><img id="imgSrc" width="150px" src="upload/${vo.image}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정">
			</td>
		</tr>
	</table>
</form>

<script>

	// 자바스크립트 =>  event 등록 : addEventListener('type',function(){})
	// 제이쿼리 => elem.on('click', function(){})
	$('#imgSrc').on("click",function(){
		
		$('#fileUpload').click(); // 이미지를 클릭하면 <input type="file">이 선택됨
		
	})
	
	// 이미지가 변경되면
	function imageChangeFnc(){
		
		//console.log($('#fileUpload')[0].files[0]);
		
		let file = $('#fileUpload')[0].files[0]; // 내가 업로드한 이미지 파일이름
		
		//console.log(file);
		
		// multipart/for-data를 요청 할떄는 FormData()를 이용
		let formData = new FormData(); // FormData() : multipart 처리를 해주는 객체임
		
		// id, file을 업로드해서 기존 DB에 저장되어있는 값을 변경해줄것임
		formData.append("id","${vo.memberId}");
		formData.append("image", file); 
		
		// 서버에 multipart / form-data : ajax 요청
		$.ajax({
			
			url : 'imageUpload.do',
			method: 'post',
			data: formData,
			contentType: false, // multipart 요청일 경우의 옵션
			processData: false, // multipart 요청일 경우의 옵션
			success: function (result){
				//console.log(result);
				
				// 화면에서도 변경할 이미지를 나타내줌
				let reader = new FileReader();
				
				reader.onload = function(evnt){
					//console.log(evnt.target)
					$('#imgSrc').attr('src', evnt.target.result); // attr : 속성 설정해주는 함수
				}
				reader.readAsDataURL(file);
			},
			error: function(err){
				console.log(err);
			}
			
		});
		
		
	}

</script>
