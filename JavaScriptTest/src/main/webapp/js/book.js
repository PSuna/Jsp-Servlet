/**
 * 
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
	{bookCode: 'P0206001', bookTitle: '이것이자바다', bookAuthor: '홍성문', bookPress: '신흥출판사', bookPrice: '20000'},
	{bookCode: 'P0206002', bookTitle: '이것이자바스크립트다', bookAuthor: '홍영웅', bookPress: '우리출판사', bookPrice: '25000'},
]

bookArrayList(); // 초기화면 : 도서 목록 출력(리스트)

// 2번 문제 : 도서 목록(리스트)
function bookArrayList(){ 
	bookList.forEach(item => {
		let tr = document.createElement("tr");
		let td = document.createElement("td");
		let input = document.createElement("input");
		input.setAttribute("type","checkbox");
		td.append(input);
		tr.append(td);
		
		td = document.createElement("td");
		td.innerText = item.bookCode;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookTitle;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookAuthor;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookPress;
		tr.append(td);

		td = document.createElement("td");
		td.innerText = item.bookPrice;
		tr.append(td);

		td = document.createElement("td");
		let button = document.createElement("button");
		button.innerText="삭제";

		td.append(button);
		tr.append(td);
		list.append(tr);
	});
}

// 3번문제 : 저장 버튼 클릭시 발생하는 이벤트
saveBtn.addEventListener("click", function(){
	// 저장버튼 여러번 클릭시 초기화(그전에 있던 리스트들이 또 등장하지않도록)
	list.innerText="";
	let book = {
							bookCode:bookCode.value,
							bookTitle:bookName.value,
							bookAuthor:author.value,
							bookPress:press.value,
							bookPrice: price.value
							};

	bookList.push(book);
	
	// 입력후 입력항목 초기화
	bookCode.value="";
	bookName.value="";
	author.value="";
	press.value="";
	price.value="";

	bookArrayList(); // 도서 목록(리스트)
});

// 4번문제 : 삭제 버튼 클릭시 삭제처리하는 이벤트함수
list.addEventListener("click",function(event){
	if(event.target.nodeName == "BUTTON"){ // 삭제 버튼을 눌렀다면
		let btn = event.target;
		btn.closest("tr").remove(); // 해당 tr태그 삭제
	}
})

// 5번문제 : 체크박스 클릭시 전체 리스트가 선택되도록 함
let chkBox = document.querySelector("#title input[type='checkbox']");
chkBox.addEventListener("click", function(){

	let selectAll = document.querySelectorAll("#list input[type='checkbox']");
		if(chkBox.checked == true){
		selectAll.forEach(item =>{
			item.checked = true;
		});
	}else{
		selectAll.forEach(item =>{
			item.checked = false;
		});
	}

});

// 6번문제 : 선택삭제 버튼 클릭시 선택된 데이터를 삭제처리하기
checkDel.addEventListener("click", function(){
	let selectAll = document.querySelectorAll("#list input[type='checkbox']");
	selectAll.forEach(item => {
		if(item.checked == true){
			item.closest("tr").remove();
		}
	});
})