<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--  dataTable을 사용하기 위한 script및 css -->
<link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>

noticeListAjax.jsp => return "notice/noticeListAjax.tiles"

서버 : noticeListJson.do => json 데이터를 반환
	
 <div>
	<button id="delBtn">삭제</button>
</div> 

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

	
	
	var table = $('#example').DataTable({
	     ajax: 'noticeListJson.do'
	});
   
    $('#example tbody').on('click', 'tr', function () {
	   
       if ($(this).hasClass('selected')) {
           $(this).removeClass('selected');
       } else {
           table.$('tr.selected').removeClass('selected');
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
   	
   		$.ajax({
   			url : "noticeRemove.do",
   			method : "post",
   			data : {nid : localStorage.getItem("noticeId")},
   			success : function(result){
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
   	
   	table.row('.selected').remove().draw(false);
   }); 
   

</script>
