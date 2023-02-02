/**
 * empList.js
 */

    // 목록 출력하기    
    let totalAry = []; // 전체목록 담아 놓을 용도

    fetch("../empListJson") //get방식
    .then(res => res.json()) // json(String) => json(객체)로 파싱 [{객체},{객체},{객체}]
    .then(result => {
      console.log(result);
      localStorage.setItem("total",result.length);  // localStorage에 result의 전체건수를 total이라는 변수에 담아놓기위한 실행문
      totalAry = result;
      //showPages(5);
      //employeeList(5);

      // 정의한 이벤트를 실행할때 dispatchEvent(정의한 이벤트)활용
      document.querySelector("#pageCnt").dispatchEvent(chgEvent);

      // item에는 result[0] => result[1].... 이 하나씩 들어감
      //result.forEach(function(item,idx,array){ // result : json(객체)들을 모아놓은 배열에서 항목하나당(객체) item에 하나씩 가져옴
      //  let tr = makeTr(item); // tr 생성후 반환
      //  list.append(tr);
      //}); // result 배열에 등록된  값의 갯수만큼 function()실행

    });

    // 저장버튼에 submit 이벤트 등록
    document.querySelector("form[name='empForm']").addEventListener("submit",addMemberFnc); // form에 submit 이벤트가 발생하면 실행하는 이벤트

    // 전체선택 체크박스
    document.querySelector("thead input[type='checkbox']").addEventListener("change", allCheckBoxChange); // input태그(checkbox)에 변화가 나타나면 실행하는 이벤트

    // 선택삭제 버튼
    document.querySelector("#delSelectedBtn").addEventListener("click",deleteCheckedFunc); // id가 delSelectedBtn인 태그를 클릭하면 발생하는 이벤트
    

    // select 태그의 change 이벤트 등록
    let chgEvent = new Event("change");
    document.querySelector("#pageCnt").addEventListener("change",changeListFnc);

    // 데이터 한건 활용해서 tr요소를 생성
    function makeTr(item){ // item은 json형식(객체)의 데이터 여야함
      let tr = document.createElement("tr");
      let titles = ["id", "lastName", "email", "hireDate", "job"]; // json(객체)의 value값을 가져올수있는 key역할을함

      titles.forEach(function(title){ // id => lastName => email ..... 등등 순서대로 가져옴
        let td = document.createElement("td");
        td.innerText = item[title]; // title은 index(key)처럼 사용 => Map<String,Object>와 비슷한 역할
        tr.append(td);
      });

      // 삭제 버튼
      let td = document.createElement("td");
      let btn = document.createElement("button");
      btn.innerText = "삭제";
      btn.addEventListener("click", deleteRowFunction); // 삭제버튼 클릭시 발생하는 이벤트
      td.append(btn);
      tr.append(td);

      // 수정 버튼
      td = document.createElement("td");
      btn = document.createElement("button");
      btn.innerText = "수정"
      btn.addEventListener("click", modifyTrFunc); // 수정버튼 클릭시 발생하는 이벤트
      td.append(btn);
      tr.append(td);

      // 체크박스
      td = document.createElement("td");
      let chk = document.createElement("input");
      chk.setAttribute("type", "checkbox"); // 속성 설정
      chk.addEventListener("change",checkAllFnc);
      td.append(chk); // <td><input type=checkbox></td>
      tr.append(td);

      // tr반환
      return tr;
    }

    // 전체 선택 체크 박스 - 개별체크박스 동기화
    function checkAllFnc(){
      /*
      // 내가 짠 코드
      let ckb = document.querySelectorAll("#list input[type='checkbox']");
      for(let i = 0; i < ckb.length; i++){
        if(ckb[i].checked != true){
          document.querySelector("thead input[type='checkbox']").checked = false;
          return;
        }
      }
      document.querySelector("thead input[type='checkbox']").checked = true;
      */

      // 교수님이 짠 코드
      // 전체건수 vs 선택건수
      let allTr = document.querySelectorAll("tbody#list tr"); // tbody의 id가 list인곳의 tr들
      let chkTr = document.querySelectorAll("tbody#list tr input[type='checkbox']:checked"); // tbody의 id가 list인곳에 체크박스에 선택되어있는것들
     
      if(allTr.length == chkTr.length){ // 전체 tr행의 갯수와 체크박스에 체크되어있는 tr행의 갯수 비교
        document.querySelector('thead input[type="checkbox"]').checked = true; // 갯수가 같으면 전체선택체크박스 선택
      }else{
        document.querySelector('thead input[type="checkbox"]').checked = false; // 다르면 전체선택체크박스 해제
      }
      console.log(allTr.length, chkTr.length);
    }

    // 삭제버튼을 누르면 이벤트가 실행될 콜백함수
    function deleteRowFunction(){
      let id = this.parentElement.parentElement.firstChild.innerText; // 첫번째 td의 값 => id
      //console.log(this.parentElement.parentElement.firstChild.innerText);
      fetch("../empListJson?del_id=" + id,{ // 쿼리스트링 : ?key=value
          method : "delete",
          headers :{
            "Content-Type" : "application/x-www-form-urlencoded"
          },
        })
      .then(resolve => resolve.json()) // {retCode : "Success"} 혹은 {retCode : "Fail"}
      .then(result =>{
        if(result.retCode == "Success"){
            alert("정상적으로 삭제");
            this.parentElement.parentElement.remove(); // 삭제 버튼을 누른 행(tr) 삭제 , remove() : 태그(노드?) 삭제
        }else if(result.retCode == "Fail"){
            alert("삭제중 오류발생");
        }
      })
      .catch(reject => console.log(reject)); // 오류가 나면 처리하는곳
    }

    // 수정화면 함수
    function modifyTrFunc(){

      // this => 수정
      let thisTr = this.parentElement.parentElement; // 수정 버튼을 누른 행(tr)을 의미함

      // 수정은 한행씩만 가능하도록만들기
      let trs = document.querySelectorAll("#list tr"); // list아래의 모든 tr을 가져와서
      trs.forEach(function(item){ // 한행마다 item으로 넘겨줌
        if(item != thisTr){ // 수정버튼을 누른행이 아니라면 실행하는 구문
          item.querySelectorAll("button").forEach(function(btn){ // 한행의 모든 버튼을 찾아서
            btn.disabled = true; // 버튼들을 disabled 해줌
          })
        }
      })
      
      //console.log("사원번호: " , thisTr.children[0].innerText); // 첫번째 td의 값
      //console.log("이름: ", thisTr.children[1].innerText);
      
      let id = thisTr.children[0].innerText; //tr안의 첫번째 td의 값
      let name = thisTr.children[1].innerText;
      let mail = thisTr.children[2].innerText;
      let hire = thisTr.children[3].innerText;
      let job = thisTr.children[4].innerText;

      // 변경할 항목 배열에 등록
      let modItems = [name, mail, hire, job]; // 이전에 input태그에 입력된 값들의 목록

      let newTr = document.createElement("tr"); // tr생성
      
      let td = document.createElement("td"); // td 생성
      td.innerText = id; // id : 변경불가항목 => id는 변경되면 안되므로
      newTr.append(td); // tr에 td => append

      // 이름변경
      modItems.forEach(item => { // item에 name => mail => hire... 순서대로 들어옴
        td = document.createElement("td"); // td생성
        let inp = document.createElement("input"); // input 생성 => type속성은 text가 디폴트임
        inp.style = "width : 100px"; // 너비 길이 설정
        inp.value = item;
        td.append(inp);
        newTr.append(td); // tr에 td => append
      })

	    // 삭제버튼만들기 : 비활성화, 변경 : DB반영
	    td = document.createElement("td");
      let btn = document.createElement("button");
      btn.innerText = "삭제";
      
      btn.disabled = true; // 버튼을 비활성화 시킴
      td.append(btn);
      newTr.append(td); // tr에 td => append

	    // 변경버튼 만들기
	    td = document.createElement("td");
      btn = document.createElement("button");
      btn.innerText = "변경";

	  // updateMemberFnc()형태는 웹브라우저가 코드를 읽어들일때 바로 실행하므로 주의해야함
	  // 따라서 updateMemberFnc 형태로 콜백함수를 줘야함
	  // 변경버튼을 눌렀을때 실행하는 이벤트 함수
      btn.addEventListener("click", updateMemberFnc);

      td.append(btn);
      newTr.append(td);

      // 체크박스 만들기
      td = document.createElement("td");
      let chk = document.createElement("input");
      chk.setAttribute("type", "checkbox"); // 속성 설정
      td.append(chk); // <td><input type=checkbox></td>
      newTr.append(td);
      
      thisTr.replaceWith(newTr); // 현재 tr을 새로운 tr로 대체시켜주는 함수
    
    }

	  // 변경버튼을 눌렀을때 실행하는 이벤트 함수
    function updateMemberFnc(){
	
    /* 현재 이런 형태임
      <tr>
        <td><td> // id
        <td><input><td> // name
        <td><input><td> // mail
        ....
      </tr>	
    */
      let currTr = this.parentElement.parentElement; // 변경 버튼을 누른 행(tr)을 의미함
      let id = currTr.children[0].innerText; // tr의 첫번째 td의 값 => 변경불가였어서 input 태그가 없음
      let name = currTr.children[1].children[0].value; // 두번째 td의 input의 value값 가져오기
      let mail = currTr.children[2].children[0].value; 
      let hDate = currTr.children[3].children[0].value; 
      let job = currTr.children[4].children[0].value; 

      //console.log(id,name,mail,hDate,job)

      fetch("../empListJson", {
        method : "post",
        headers : {"Content-Type" : "application/x-www-form-urlencoded"},
        body : "param=update&id="+id+"&name="+name+"&mail="+mail+"&hire="+hDate+"&job="+job // key=value&key2=value2...
      })
      .then(resolve => resolve.text()) // '{"retCode":"Success"}' : json(String)으로 반환
      .then(result => { 
        //console.log(result);
        
        // indexOf("문자열") : 해당 문자열을 찾아서 시작위치를 리턴
        if(result.indexOf("Success") != -1){ // Success라는 문자열이 포함되어있다면
          alert("정상적으로 처리");
          
          // makeTr에 넘겨주는 매개변수 값은 json(객체) 형식이어야함
          // ★ makeTr의 변수인 let titles = ["id", "lastName", "email", "hireDate", "job"];와 key이름이 일치해야함
          let newTr = makeTr({id:id, lastName:name, email:mail, hireDate:hDate, job:job});
          
          currTr.replaceWith(newTr); // 현재 tr을 새로운 tr로 대체
        }else{ // Fail 이라면
          console.log("error 발생..");
        }
        
       })
      .catch(reject => console.log(reject))

      // 수정 이벤트 함수때 설정(다른버튼 disabled) 해놓았던거 복구
      let trs = document.querySelectorAll("#list tr"); // list아래의 모든 tr을 가져와서
      trs.forEach(function(item){ // 한행마다 item으로 넘겨줌
          item.querySelectorAll("button").forEach(function(btn){ // 한행의 모든 버튼을 찾아서
            btn.disabled = false;
          })
      })

    }

    // form에 저장 버튼을 눌렀을때 실행하는 함수 => 저장 처리하는 함수
    function addMemberFnc(evnt){
      evnt.preventDefault(); // form태그의 action속성에서 설정한 페이지로(../myinfo) 이동하는것을 막아주는 역할
      console.log("여기에 출력");

      // form의 input태그들의 value값을 가져옴
      let id = document.querySelector('input[name="emp_id"]').value; 
      let name = document.querySelector('input[name="last_name"]').value;
      let mail = document.querySelector('input[name="email"]').value;
      let hDate = document.querySelector('input[name="hire_date"]').value;
      let job = document.querySelector('input[name="job_id"]').value;

      if(!id || !name || !mail || !hDate || !job){ // 값들이 하나라도 없다면(입력되어있지 않다면)
        alert("필수 입력값을 확인!!!");
        return; // 함수 종료
      }

      fetch('../empListJson',{
        method : 'post',
        headers : {"Content-Type" : "application/x-www-form-urlencoded"}, 
        body : "id=" + id + "&name=" + name + "&mail=" + mail + "&hire=" + hDate + "&job=" + job // key = val&key2=val2
      })
      .then(resolve => resolve.json()) 
      .then(result =>{ // {retCode : "Success"} 혹은 {retCode : "Fail"}
        if(result.retCode == "Success"){
          alert("정상처리");
          
          // makeTr에 넘겨주는 매개변수 값은 json(객체) 형식이어야함
          // ★ makeTr의 변수인 let titles = ["id", "lastName", "email", "hireDate", "job"];와 key이름이 일치해야함
          list.append(makeTr({id:id, lastName:name, email:mail, hireDate:hDate, job:job})); 

          // 입력항목 초기화
          document.querySelector('input[name="emp_id"]').value = "";
          document.querySelector('input[name="last_name"]').value = "";
          document.querySelector('input[name="email"]').value = "";
          document.querySelector('input[name="hire_date"]').value = "";
          document.querySelector('input[name="job_id"]').value = "";

        }else if(result.retCode == "Fail"){
          alert("처리중 에러!");
        }
      })

    }

    // 전체 선택 체크박스를 눌렀을때 실행하는 이벤트 함수
    function allCheckBoxChange(){
      //console.log(this.checked);

      // tbody에 있는 <input type=checkbox>들을 모두 가져와서 => 하나씩(forEach의 chk) 전체 선택 체크박스 상태로 바꿔줌
      document.querySelectorAll("tbody input[type='checkbox']").forEach(chk => {
        chk.checked = this.checked;
      })

    }

    // // 선택삭제 처리
    // // fetch API => 비동기방식처리
    // function deleteCheckedFunc(){
	  // // list아래의 체크되어 있는 <input type=checkbox>들을 모두 가져와서 => 하나씩(forEach의 chk) 삭제
    //   document.querySelectorAll('#list input[type="checkbox"]:checked').forEach(chk =>{
    //     let id = chk.parentElement.parentElement.firstChild.innerText; // tr의 첫번째 td의값 = checked된 해당행의 id값
    //     fetch("../empListJson?del_id=" + id,{
    //       method : "delete",
    //       headers :{
    //         "Content-Type" : "application/x-www-form-urlencoded"
    //       },
    //     })
    //     .then(resolve => resolve.json()) // {retCode : "Success"} 혹은 {retCode : "Fail"}
    //     .then(result =>{
    //       if(result.retCode == "Success"){ // 삭제 성공
    //           chk.parentElement.parentElement.remove(); // checked된 해당행(tr) 삭제
    //       }else if(result.retCode == "Fail"){ // 삭제 실패
    //          //alert("삭제중 오류 발생");
    //       }
    //     })
    //     .catch(reject => console.log(reject));

    //     })
    // }

    // 선택삭제 처리
    // fetch API => 비동기방식처리 => (async, await)을 사용해서 동기식처리로 바꾸기 
    // => await는 async안에서만 사용가능하고 promise객체에만 붙일수있음
    // html : id 값을 선택 삭제처리
    // 서블릿 : id값을 받아서 성공/실패 => 반환 {id:100, retCode:Success/Fail}
    // html : [{id:100, retCode : Success}, {id:101, retCode: Fail}....]
    async function deleteCheckedFunc(){
      let ids = [];
	    
      // list아래의 체크되어 있는 <input type=checkbox>들을 모두 가져와서 => 하나씩(forEach의 chk) 삭제
      let chks = document.querySelectorAll('#list input[type="checkbox"]:checked')
      
      for(let i = 0; i < chks.length; i++){
        let id = chks[i].parentElement.parentElement.firstChild.innerText; // tr의 첫번째 td의값 = checked된 해당행의 id값
        let resp = await fetch("../empListJson?del_id=" + id,{
          method : "delete",
          headers :{
            "Content-Type" : "application/x-www-form-urlencoded"
          },
        })

        // resp에 json(String)형태 : {id:100, retCode:Success/Fail} 반환됨
        // json(객체)로 파싱
        let json = await resp.json(); // await : 위에 resp변수가 처리될때까지 기다림 
        console.log(json);
        ids.push(json); // ids : 삭제한 id들의 배열 [{id:101,retCode:Success},{id:102,retCode:Success}]
      }
        //console.log('ids>>>', ids); // alert(101,102,103 삭제했습니다!)

        // DB에서 delete한걸 화면에 삭제해주는 함수
        // => 삭제한 id들의 배열 [{id:101,retCode:Success},{id:102,retCode:Success}]
        processAfterFetch(ids); 

    }

    // 화면처리
    function processAfterFetch(ary=[]){ // ary는 배열을 의미함 : 삭제한 id들의 배열 [{id:101,retCode:Success},{id:102,retCode:Success}]
      let targetTr = document.querySelectorAll("#list tr");

      console.log(targetTr, ' vs ', ary);

      // targetTr vs ary
      targetTr.forEach(tr => { // list아래의 모든 tr을 하나씩 가져와서 tr에 저장
        for(let i = 0; i < ary.length; i++){
          if(tr.children[0].innerText == ary[i].id){ // tr의 첫번째자식 => id값 과 arry배열의 id값을 비교해서 같다면
            if(ary[i].retCode == "Success"){ // retCode가 Success인 조건하에 해당행 삭제
              tr.remove(); 
            }else{ // 실패시 style을 그레이로줌
              tr.setAttribute("class","delError");
            }
          }
        }
      })
    }

    // 페이지 목록()
    function showPages(curPage = 5){ // curPage : 현재 출력할 페이지
      // init. 초기화
      document.querySelectorAll("#paging a").forEach(item => item.remove()); // paging태그 아래의 모든 a태그들을 삭제

      let pageCnt = parseInt(localStorage.getItem("pageCnt"));

      // 전체건수
      let totalCnt = parseInt(localStorage.getItem("total"));  // localStorage에 result의 전체건수를 total이라는 변수에 담아놓은 것을 가져옴
      
      let endPage = Math.ceil(curPage / 10) * 10; // 끝페이지, Math.ceil : 올림
      let startPage = endPage  - 9; // 시작 페이지
      let realEnd = Math.ceil(totalCnt / pageCnt); // 진짜 마지막 페이지
      let prev,next; // 이전 페이지목록 , 다음 페이지목록

      endPage = endPage > realEnd ? realEnd : endPage; // 끝페이지 > 진짜 마지막페이지보다 크다면 진짜마지막페이지를 저장
      prev = startPage > 1 ? true : false; // startPage가 1보다 크면 true
      next = endPage < realEnd ? true : false; // endPage가 진짜마지막페이지보다 작다면 true

      let paging = document.getElementById("paging");

      // prev & next
      if(prev){ // 이전 페이지가 있다면 (시작페이지가 1이 아니라면)
        let aTag = document.createElement('a'); // a 태그 생성
        aTag.addEventListener("click", showListPages);
        aTag.href = "#"; // a태그의 href의 속성 
        aTag.page = startPage-1; // innerText 속성이 페이지값을 활용
        aTag.innerHTML= '&laquo'; //startPage-1;
        paging.append(aTag); // <div><a href="">1</a><ahref="">2</a>....</div>
      }

      for(let i = startPage; i <= endPage; i++){ // 시작페이지 ~ 끝페이지
        let aTag = document.createElement('a'); // a 태그 생성
        aTag.addEventListener("click", showListPages);
        aTag.href = "#"; // a태그의 href의 속성 
        aTag.innerText = i;
        aTag.page = i; // innerText 속성이 페이지값을 활용
        if(i == curPage){
          aTag.className = "active"; // aTag.setAttribute("class", "active");
        }
        paging.append(aTag); // <div><a href="">1</a><ahref="">2</a>....</div>
      }

      if(next){ // 다음 페이지가 있다면(진짜 마지막 페이지가 아니라면)
        let aTag = document.createElement('a'); // a 태그 생성
        aTag.addEventListener("click", showListPages);
        aTag.href = "#"; // a태그의 href의 속성 
        aTag.page = endPage + 1 ; // innerText 속성이 페이지값을 활용
        aTag.innerHTML = '&raquo;'; //endPage + 1 ;
        paging.append(aTag); // <div><a href="">1</a><ahref="">2</a>....</div>
      }

    }

    // 페이지 클릭하면 페이지 목록 & 사원목록
    function showListPages(e){
      //console.log(e.target.innerText); 
      let page = e.target.page; // 내가 클릭한 a태그에 속성을 넣음
      showPages(page); // 내가 클릭한 페이지의 목록을 보여줌
      employeeList(page); // 내가 클릭한 페이지의 사원목록을 보여줌
    }

    // 사원 목록()
    function employeeList(curPage = 5){ // 현재 출력할 페이지

      //init. 
      document.querySelectorAll("#list tr").forEach(item => item.remove()); // list 아래의 tr들을(사원목록 행) 모두 제거
      
      let pageCnt = parseInt(localStorage.getItem("pageCnt"));
      console.log("pageCnot: ",pageCnt);

      // 1~10 , 11 ~ 20...
      let end = curPage * pageCnt; // 끝목록
      let start = end - (pageCnt-1); // 시작 목록

      let newList = totalAry.filter((emp, idx) => { // totalAry : 전체 사원목록 배열
        // idx와 totalAry는 0부터 시작 , 0 ~ 9 => 10개
        // start(1부터시작) ~ end-1 사이의 목록에 해당하는 것들만 newList변수에 저장됨
        return (idx+1) >= start && idx < end; 
      })

      let lst = document.getElementById("list");

      newList.forEach(emp => { // start(1부터시작) ~ end-1 사이의 목록에 해당하는 것들만 
        let tr = makeTr(emp); // tr을 만듦
        lst.append(tr);
      })

    }

    function changeListFnc(e){
      localStorage.setItem("pageCnt",this.value); // select option의 value 값이 pageCnt에 저장이됨
      // 페이지건수 변경이 되면 첫페이지부터 보여주도록 한다.
      showPages(1); // 내가 클릭한 페이지의 목록을 보여줌
      employeeList(1); // 내가 클릭한 페이지의 사원목록을 보여줌
    }