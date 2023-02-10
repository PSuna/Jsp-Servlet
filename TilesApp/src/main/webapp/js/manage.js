/**
 *  manage.js
 */

console.log("manage.js start....");

function updateMemberFnc(e){
		
	// modifyMember.do 사용자 정보 수정.
	let tr =  $(e.target).parent().parent(); // tr
	 
	// children eq를 안쓰고 val()값 찾아오는 방법
	console.log(tr.find('td:nth-of-type(2) input').val()); 
	
	// input 태그의 클래스가 name인 애의 value 속성을 읽어옴
	console.log(tr.find('input.phone').val());
	
	//==========================================================
	
	let id = tr.find('td:nth-of-type(1)').text();
	let pass = tr.find('td:nth-of-type(1)').text();
	let name = tr.find('input.name').val();
	let phone = tr.find('input.phone').val();
	let addr = tr.find('input.addr').val();
	let resp = tr.find('input.auth').val();

	console.log(id, name, addr)
	$.ajax({	
			url: 'modifyMember.do',
			method: 'post', // get , put , post 가능함
			data : {param:'ajax',mid:id, mname:name, mphone:phone, maddr:addr, resp:resp},
			success: function(result){
				if(result.retCode == 'Success'){
					let member = {mid:id, mname:name, mphone:phone, maddr:addr, resp:resp};
					$('#list').append(makeRow(member));
					
				}else{
					alert("수정 오류!!");
				}
			},
			error: function(reject){
				console.log(reject);
				
			}
		})
	
} // end of updateMemberFnc()

	// member값을 가져오면 tr을 만들어서 append 하기
	function makeRow(member={}){ 
		
		// member값을 활용해서 tr생성
		let tr = $('<tr/>'); // document.createElement('tr');
		
		tr.on('dblclick', function(e){
			
			// chilren을 통해 가지고온 하위요소중에서 첫번째값을 가져오라는말
			let id = $(this).children().eq(0).text();
			let name = $(this).children().eq(1).text();
			let phone = $(this).children().eq(2).text();
			let addr = $(this).children().eq(3).text();
			let resp = $(this).children().eq(4).text();
			
			let nTr = $('<tr/>').append(
				
				$('<td/>').text(id),
				$('<td/>').append($('<input class="name"/>').val(name)),
				$('<td/>').append($('<input class="phone"/>').val(phone)),
				$('<td/>').append($('<input class="addr"/>').val(addr)),
				$('<td/>').append($('<input class="auth"/>').val(resp)),
				$('<td/>').append($('<button onclick="updateMemberFnc(event)">수정</button>')) // onclick="updateMemberFnc(event)"
			
			)
			
			
			/* clone 할때
			nTr = $('#template tr').clone(true);
			nTr.find('input.name').val(name);
			nTr.find('input.name').val(name);
			*/
			
			
			// 기존의 tr을 새로운 tr로 대체하겠다는말
			$(this).replaceWith(nTr); 
		
		})
		
		tr.append(
			
			$('<td/>').text(member.memberId),
			$('<td/>').text(member.memberName),
			$('<td/>').text(member.memberPhone),
			$('<td/>').text(member.memberAddr),
			$('<td/>').text(member.responsibility),
			$('<td/>').append(
				// deleteMemberFnc : 콜백함수를 의미함
				// deleteMemberFnc() : 바로 실행되어짐
				// attr : 속성값을 넣을수있음
				$('<button>삭제</button>').attr("mid",member.memberId).on('click', deleteMemberFnc) 
			)
		);
		return tr;
	}
	
	function deleteMemberFnc(e){
		
		if(!window.confirm("삭제하시겠습니까?")){
			return; // 함수를 종료하면됨
		}
		
		let user = $(e.target).attr("mid"); // mid 속성값을 가져온다는말
		$.ajax({
			
			url: 'removeMember.do',
			data : {id :user}, // removeMember.do?id=user라는 의미
			success: function(result){
				if(result.retCode == 'Success'){
					$(e.target).parent().parent().remove();
					//$(e.target).parent().parent().remove(); // parent() : 부모태그를 의미함
				}else{
					alert("삭제오류!!");
				}
			},
			error: function(reject){
				console.log(reject);
			}
		});
		
	} // end of deleteFnc
	
	
// document를 끝까지 다읽어들인 다음에 해당함수를 실행하도록 하기
$(document).ready(function(){
	
	/* clone하는법  ============================================= */
	
	let clone = $('#template').clone(true); // true : 하위에 있는 구조  , false : 상위에 있는 구조
	console.log(clone.find('tr'));
	let tr = clone.find('tr');
	tr.find('.name').val('test');
	$('#list').append(tr);
	
	
	// ========================================================
	
	
	// 목록 가져오는 aJax 호출
	console.log($('#list'));
	
	// fetch 대신 제이쿼리에서 제공해주는 aJax를 사용함
	$.ajax({
		
		url: 'memberList.do',
		success : function(result){
			console.log(result);
			$(result).each(function(idx, item){ // 자바스크립트와는 forEach 순서가 다름
				$('#list').append(makeRow(item));
			})
		},
		error : function(reject){
			console.log(reject);
		}
		
	})
	
	// 등록이벤트
	$('#addBtn').on('click', function(){
		
		let id = $('#mid').val(); // element.value 속성 읽어옴.
		let name = $('#mname').val();
		let phone = $('#mphone').val();
		let addr = $('#maddr').val();
		let img = $('#mimage')[0].files[0];
		
		let formData = new FormData();
		formData.append('id',id);
		formData.append('name',name);
		formData.append('phone',phone);
		formData.append('addr',addr);
		formData.append('img',img);
		
		console.log(id,name,phone,addr,img);
		
		$.ajax({
			
			url: 'addMember.do',
			method: 'post',
			data : formData,
			contentType: false,
			processData: false,
			success: function(result){
				// 처리된 정보를 화면에 생성해줌
				console.log(result);
				if(result.retCode == 'Success'){
					$('#list').append(makeRow(result.member));
				}else{
					alert("입력미완!");
				}
			},
			error: function(reject){
				console.log(reject);
			}
		})
	})

}); 
 
