/**
 * empList.js
 */

    // 목록 출력하기    
    fetch("../empListJson")
    .then(res => res.json())
    .then(result => {
      // item에는 result[0] => result[1].... 이 하나씩 들어감
      result.forEach(function(item,idx,array){ // result : json(객체)들을 모아놓은 배열에서 항목하나당 item에 하나씩 가져옴
        let tr = makeTr(item); // tr 생성후 반환
        list.append(tr);
      }); // result 배열에 등록된  값의 갯수만큼 function()실행
    });

    // 저장버튼에 submit 이벤트 등록
    document.querySelector("form[name='empForm']").addEventListener("submit",addMemberFnc); // form에 submit 이벤트가 발생하면 실행하는 이벤트

    // 전체선택 체크박스
    document.querySelector("thead input[type='checkbox']").addEventListener("change", allCheckBoxChange); // input태그(checkbox)에 변화가 나타나면 실행하는 이벤트

    // 선택삭제 버튼
    document.querySelector("#delSelectedBtn").addEventListener("click",deleteCheckedFunc); // id가 delSelectedBtn인 태그를 클릭하면 발생하는 이벤트


    // 데이터 한건 활용해서 tr요소를 생성
    function makeTr(item){ // item은 json형식(객체)의 데이터 여야함
      let tr = document.createElement("tr");
      let titles = ["id", "lastName", "email", "hireDate", "job"]; // json(객체)의 value값을 가져올수있는 key역할을함

      titles.forEach(function(title){ // id => lstName => email ..... 등등 순서대로 가져옴
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
      td.append(chk); // <td><input type=checkbox></td>
      tr.append(td);

      // tr반환
      return tr;

    }

    // 삭제버튼을 누르면 이벤트가 실행될 콜백함수
    function deleteRowFunction(){
      let id = this.parentElement.parentElement.firstChild.innerText;
      //console.log(this.parentElement.parentElement.firstChild.innerText);
      fetch("../empListJson?del_id=" + id,{
          method : "delete",
          headers :{
            "Content-Type" : "application/x-www-form-urlencoded"
          },
        })
      .then(resolve => resolve.json())
      .then(result =>{
        if(result.retCode == "Success"){
            alert("정상적으로 삭제");
            this.parentElement.parentElement.remove();
        }else if(result.retCode == "Fail"){
            alert("삭제중 오류발생");
        }
      })
      .catch(reject => console.log(reject));
    }

    // 수정화면 함수
    function modifyTrFunc(){

      // this => 수정
      let thisTr = this.parentElement.parentElement;
      console.log("사원번호: " , thisTr.children[0].innerText); // 첫번째 td의 값
      console.log("이름: ", thisTr.children[1].innerText);
      let id = thisTr.children[0].innerText;
      let name = thisTr.children[1].innerText;
      let mail = thisTr.children[2].innerText;
      let hire = thisTr.children[3].innerText;
      let job = thisTr.children[4].innerText;

      // 변경할 항목 배열에 등록
      let modItems = [name, mail, hire, job];

      let newTr = document.createElement("tr");
      let td = document.createElement("td");
      td.innerText = id; // 변경불가항목
      newTr.append(td);

      // 이름변경
      modItems.forEach(item => {
        td = document.createElement("td");
        let inp = document.createElement("input");
        inp.style = "width : 100px";
        inp.value = item;
        td.append(inp);
        newTr.append(td);
      })


      // 삭제 : 비활성화, 변경 : DB반영
      let btn = document.createElement("button");
      btn.innerText = "삭제";
      btn.disabled = true; // 비활성화 버튼
      td = document.createElement("td");
      td.append(btn);
      newTr.append(td);

      // 변경버튼
      btn = document.createElement("button");
      btn.innerText = "변경";

      btn.addEventListener("click", updateMemberFnc); // updateMemberFnc()는 웹브라우저가 코드읽어들일때 실행되므로 주의해야함

      td = document.createElement("td");
      td.append(btn);
      newTr.append(td);
      thisTr.replaceWith(newTr);

    }

    function updateMemberFnc(){
      let currTr = this.parentElement.parentElement;
      let id = currTr.children[0].innerText;
      let name = currTr.children[1].children[0].value; // td - input의 value값 가져오기
      let mail = currTr.children[2].children[0].value;
      let hDate = currTr.children[3].children[0].value;
      let job = currTr.children[4].children[0].value;

      console.log(id,name,mail,hDate,job)

      fetch("../empListJson", {
        method : "post",
        headers : {"Content-Type" : "application/x-www-form-urlencoded"},
        body : "param=update&id="+id+"&name="+name+"&mail="+mail+"&hire="+hDate+"&job="+job
      })
      .then(resolve => resolve.text())
      .then(result => { 
        console.log(result);
        if(result.indexOf("Success") != -1){ // Success라는 문자열이 포함되어있다면
          alert("정상적으로 처리");
          let newTr = makeTr({id:id, lastName:name, email:mail, hireDate:hDate, job:job});
          currTr.replaceWith(newTr);
        }else{
          console.log("error 발생..");
        }
        
       })
      .catch(reject => console.log(reject))

    }

    // 저장 처리하는 함수
    function addMemberFnc(evnt){
      evnt.preventDefault(); // form태그의 action속성에서 설정한 페이지로 이동하는것을 막아주는 역할
      console.log("여기에 출력");

      // form의 input태그들의 value값을 가져옴
      let id = document.querySelector('input[name="emp_id"]').value; 
      let name = document.querySelector('input[name="last_name"]').value;
      let mail = document.querySelector('input[name="email"]').value;
      let hDate = document.querySelector('input[name="hire_date"]').value;
      let job = document.querySelector('input[name="job_id"]').value;

      if(!id || !name || !mail || !hDate || !job){ // 값들이 하나라도 없다면
        alert("필수 입력값을 확인!!!");
        return;
      }

      fetch('../empListJson',{
        method : 'post',
        headers : {"Content-Type" : "application/x-www-form-urlencoded"}, // key = val&key1=val1
        body : "id=" + id + "&name=" + name + "&mail=" + mail + "&hire=" + hDate + "&job=" + job
      })
      .then(resolve => resolve.json())
      .then(result =>{
        if(result.retCode == "Success"){
          alert("정상처리");
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

    // 전체 선택 체크박스
    function allCheckBoxChange(){
      console.log(this.checked);

      // tbody에 있는 체크박스 선택
      document.querySelectorAll("tbody input[type='checkbox']").forEach(chk => {
        chk.checked = this.checked;
      })

    }

    // 선택삭제 처리
    function deleteCheckedFunc(){
      document.querySelectorAll('tbody input[type="checkbox"]:checked').forEach(chk =>{
        let id = chk.parentElement.parentElement.firstChild.innerText;
        fetch("../empListJson?del_id=" + id,{
          method : "delete",
          headers :{
            "Content-Type" : "application/x-www-form-urlencoded"
          },
        })
        .then(resolve => resolve.json())
        .then(result =>{
          if(result.retCode == "Success"){
              chk.parentElement.parentElement.remove();
          }else if(result.retCode == "Fail"){
             
          }
        })
        .catch(reject => console.log(reject));

        })
    }