/**
 * 
 */

// 목록 출력하기    
    fetch("../devBoardJson") //get방식
    .then(res => res.json()) // json(String) => json(객체)로 파싱 [{객체},{객체},{객체}]
    .then(result => {
      // item에는 result[0] => result[1].... 이 하나씩 들어감
      result.forEach(function(item,idx,array){ // result : json(객체)들을 모아놓은 배열에서 항목하나당(객체) item에 하나씩 가져옴
        let tr = makeTr(item); // tr 생성후 반환
        list.append(tr);
      }); // result 배열에 등록된  값의 갯수만큼 function()실행
    });

    // 저장버튼에 submit 이벤트 등록
    document.querySelector("#boardInsertBtn").addEventListener("submit",addBoardFnc); // form에 submit 이벤트가 발생하면 실행하는 이벤트

     // 데이터 한건 활용해서 tr요소를 생성
    function makeTr(item){ // item은 json형식(객체)의 데이터 여야함
      let tr = document.createElement("tr");
      let titles = ["id", "title", "content", "language", "userId", "wDate", "view"]; // json(객체)의 value값을 가져올수있는 key역할을함

      titles.forEach(function(title){ // id => lastName => email ..... 등등 순서대로 가져옴
        let td = document.createElement("td");
        td.innerText = item[title]; // title은 index(key)처럼 사용 => Map<String,Object>와 비슷한 역할
        tr.append(td);
      });

      // 삭제 버튼
      let td = document.createElement("td");
      let btn = document.createElement("button");
      btn.innerText = "삭제";
      //btn.addEventListener("click", deleteRowFunction); // 삭제버튼 클릭시 발생하는 이벤트
      td.append(btn);
      tr.append(td);

      // 수정 버튼
      td = document.createElement("td");
      btn = document.createElement("button");
      btn.innerText = "수정"
      //btn.addEventListener("click", modifyTrFunc); // 수정버튼 클릭시 발생하는 이벤트
      td.append(btn);
      tr.append(td);

      // 체크박스
      td = document.createElement("td");
      let chk = document.createElement("input");
      chk.setAttribute("type", "checkbox"); // 속성 설정
      //chk.addEventListener("change",checkAllFnc);
      td.append(chk); // <td><input type=checkbox></td>
      tr.append(td);

      // tr반환
      return tr;
    }

    // form에 저장 버튼을 눌렀을때 실행하는 함수 => 저장 처리하는 함수
    function addBoardFnc(evnt){
      evnt.preventDefault(); // form태그의 action속성에서 설정한 페이지로(../myinfo) 이동하는것을 막아주는 역할
      //console.log("여기에 출력");

      // form의 input태그들의 value값을 가져옴
      let id = document.querySelector('input[name="board_user_id"]').value; 
      let language = document.querySelector('input[name="board_language"]').value;
      let title = document.querySelector('input[name="board_title"]').value;
      let content = document.querySelector('input[name="board_content"]').value;

      if(!id || !language || !title || !content){ // 값들이 하나라도 없다면(입력되어있지 않다면)
        alert("필수 입력값을 확인!!!");
        return; // 함수 종료
      }

      fetch('../devBoardJson',{
        method : 'post',
        headers : {"Content-Type" : "application/x-www-form-urlencoded"}, 
        body : "id=" + id + "&language" + language + "&title=" + title + "&content=" + content // key = val&key2=val2
      })
      .then(resolve => resolve.json()) 
      .then(result =>{ // {retCode : "Success"} 혹은 {retCode : "Fail"}
        if(result.retCode == "Success"){
          alert("정상처리");
          
          // makeTr에 넘겨주는 매개변수 값은 json(객체) 형식이어야함
          // ★ makeTr의 변수인 let titles = ["id", "title", "content", "language", "userId", "wDate", "view"];와 key이름이 일치해야함
          list.append(makeTr({id:id, title:title, content:content, language:language, userId:id, wDate:wDate,view:view })); 

          // 입력항목 초기화
          document.querySelector('input[name="board_user_id"]').value = "";
          document.querySelector('input[name="board_language"]').value = "";
          document.querySelector('input[name="board_title"]').value = "";
          document.querySelector('input[name="board_content"]').value = "";

        }else if(result.retCode == "Fail"){
          alert("처리중 에러!");
        }
      })

    }