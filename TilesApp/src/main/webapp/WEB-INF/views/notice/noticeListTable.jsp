<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSTL : 조건문, 반복문 등을 사용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 날짜 포멧 지정하기위해 사용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>


<div>
	작성자:<input type="text" id="writer" value="user1" readonly>
	제목:<input type="text" id="title">
	내용:<input type="text" id="subject">
	<button id="addBtn">저장</button>
	<button id="delBtn">삭제</button>
</div>

<br>

<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>글번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
                <th>작성일자</th>
            </tr>
        </thead>
        
        <tbody>
        	<!--  forEach : 반복문 -->
        	<c:forEach var="notice" items="${noticeList}">
	        	<tr>
	        		<td>${notice.noticeId}</td>
	        		<td>${notice.noticeWriter}</td>
	        		<td>${notice.noticeTitle}</td>
	        		<td>${notice.hitCount}</td>
	        		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.noticeDate}"/></td>
	        	</tr>
        	</c:forEach>
        </tbody>
        
        <tfoot>
            <tr>
                <th>글번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
                <th>작성일자</th>
            </tr>
        </tfoot>
</table>

<script>

	var t = $('#example').DataTable();
	
	// addEventListener
	$('#addBtn').on('click', function () {
		
		var formData = new FormData; // multipart 요청시 사용하는 객체
		
		formData.append("writer", $("#writer").val()); // input태그의 value값
		formData.append("title", $("#title").val());
		formData.append("subject", $("#subject").val());
		
		// db insert후 화면처리 해야함
		$.ajax({
			url : 'noticeAddJson.do',
			method: 'post',
			data: formData,
			contentType: false, // multipart 요청일 경우의 옵션
			processData: false, // multipart 요청일 경우의 옵션
			
			success: function(result){ // result : 추가할 vo객체(object) , 자동적으로 json 객체로 파싱해줌
				//console.log(result);
				// 화면처리
				t.row.add([result.noticeId, result.noticeWriter, result.noticeTitle, result.hitCount, result.noticeDate]).draw(false);
		        // t.row.add([ , , , , ]).draw(false);
			},
			error: function(reject){
				console.log(reject);
			}
			
		});
		
       // counter++;
    });
 
    // Automatically add a first row of data
    // $('#addBtn').click();
    
    // tr 선택하면 스타일 변경.
    $('#example tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) { // $(this) : tr을 의미함 => selected가 되어있다면 
            $(this).removeClass('selected');
        } else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        
     	// tr의 자식태그들중 첫번째 td의 innerText값 가져오기
        console.log($(this).children().eq(0).text()); 
     	
     	// 클릭할때마다 localStorage에 noticeId라는 이름으로 값을 저장하기 => 값들이 계속 덮어씌어짐
     	// 나중에 삭제버튼을 누를때 필요한 정보임
     	localStorage.setItem("noticeId",$(this).children().eq(0).text());
     	
    });
    
    // 삭제버튼
    $("#delBtn").on("click",function(){
    	console.log("삭제할 글번호: " + localStorage.getItem("noticeId"));
    	// t.row(".selected") : 
    	//console.log("삭제할 글번호2: " + t.row(".selected")); 
    	t.row('.selected').remove().draw(false);
    	
    });
    
</script>