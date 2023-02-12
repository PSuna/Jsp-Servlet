<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!-- 날짜의 format(형식)을 줄수있음 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- jQuery : CDN방식으로 추가함 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    
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
<br>
<div>
	<span><b>제목:</b></span><span><input type="text" id="title"></span>
	<span><b>내용:</b></span><span><input type="text" id="subject"></span>
	<span><b>작성자:</b></span><span><input type="text" id="writer" value="${logId}" readonly></span>
	<button class="btn btn-primary" id="addReply">댓글등록</button>
</div>
<br>
	<table border="1" style="margin:auto; width:80%;">
		<thead>
			<tr style="border: 1px solid green;">
				<th colspan="4">댓글정보</th>
			</tr>
		</thead>
		<tbody id="list">
		</tbody>
	</table>
	
<script>

let nid = ${vo.noticeId}; 
let logId = '${logId}';

	fetch('replyList.do?nid='+nid)
	.then(resolve => resolve.json())
	.then(result => {
		console.log(result);
		result.forEach(reply => {
			makeTr(reply);
		})
	})
	.catch(err => {
		console.log(error);
	})
	
	// 삭제 버튼 클릭시 => 삭제버튼을 누른 해당(tr)행을 삭제하기 위해 
	// makeTr시 해당행(tr)의 data-id속성값(id)을 매개변수로 넘겨줌
	function deleteReply(replyId){ 
		// 제이쿼리의 ajax 호출. == 자바스크립트의 fetch() 와 같은 기능임
		$.ajax({
			url: 'removeReply.do', //호출하는 url 패턴을 입력
			method: 'post', // delete는 여기서 안됨 post방식만됨
			data : {rid: replyId}, // 넘겨줄 data
			success : function(result){ // result : retCode의 값이 Success/Fali
				console.log(result);
				if(result.retCode == 'Success'){
					$("tr[data-id="+replyId+"]").remove();
				}else{
					alert("처리안됨!!");
				}
			
			},
			error : function(reject){
				console.log(reject);
				
			}
		})
		
		// <tr data-id=3> => tr요소를 가져오기 위해 tr[data-id=3]로 설정
		// tr[data-id=3] : tr의 data-id 속성값이 3인 애를 가져오겠다는말
		//$("tr[data-id="+replyId+"]").remove();
	}
	
	
	// addEventListener 기능
	$("#addReply").on('click', function(){	
		let writer = $('#writer').val(); // val() : input 태그의 value값 읽어오는것
		let subject = $('#subject').val();
		let title = $('#title').val();
		
		$.ajax({
			url:'addReply.do',
			method:'post',
			data:{title: title, writer: writer, subject: subject, nid: nid},
			success: function(result){ // result : 추가할 vo 객체
				console.log(result);
				makeTr(result); // 새로운 row 생성한후 tbody아래에 append.
			},
			error: function(reject){
				console.log(reject);
			}
		})
		
		// val() : input 태그의 value값 초기화
		$('#subject').val("");
		$('#title').val("");
		
	})
	
	function makeTr(reply){
		// tr : 댓글번호, 제목, 작성자, 작성일자
		// tr : 댓글내용
		let tr1 = $('<tr />').attr('data-id', reply.replyId).append( // 자식태그로 추가
							
							// text : innerText와 같은말 / html : innerHTML같은말
							$("<td />").html("<b>번호:</b>" + reply.replyId), 
							$("<td />").html("<b>제목:</b>" + reply.replyTitle),
							$("<td />").html("<b>작성자:</b>" + reply.replyWriter),
							$("<td />").html("<b>날짜:</b>" + reply.replyDate)
		
						   )
						   
		let tr2 = $('<tr />').attr('data-id', reply.replyId).append(
						$("<td colspan='4' />").html(function(){
						
						if(reply.replyWriter == logId){
							// 주의!! ;(세미콜론) 없어야함
							return reply.replySubject+ "<button onclick='deleteReply("+reply.replyId+")' class='btn btn-warning'>삭제</button>" 
						}else{
							// 주의!! ;(세미콜론) 없어야함
							return reply.replySubject
						}
						
						})
					)
							
		console.log(tr1, tr2);
		$("#list").prepend(tr1, tr2);
	}
	
</script>
