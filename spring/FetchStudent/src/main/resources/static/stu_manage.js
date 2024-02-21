
function fetchSelect(){
    const classCode =document.querySelector("#class-selector").value;
    fetch('/stu/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           classCode : classCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        //기존 테이블 내용 삭제
        const tbodyTag = document.querySelector('#stu-list-table > tbody');
        
        //tbody태그 안의 모든 내용을 삭제
        tbodyTag.innerHTML = '';

        //새로 조회한 데이ㅌ로 tbody 안의 내용을 채워 줌.
                   
                let str =``;
                //     for(const stu of data){
                //             str += `<tr>
                //             <td>${}</td>
                //             <td>${stu.className}</td>
                //             <td>${stu.stuNum}</td>
                //             <td>${stu.stuName}</td>
                //         </tr>`;
             
                //     }
                       
                    // element(stu), index(i) = stu of data 맘대로 써도됌
                    data.forEach(function(stu, i){
                        str += `
                        <tr>
                        <td>${data.length-i}</td>
                        <td>${stu.className}</td>
                        <td>${stu.stuNum}</td>
                        <td>
                        <span onclick="studentScore(${stu.stuNum})" id="td-span">
                        ${stu.stuName}
                        </span>
                        </td>
                    </tr>`;
        
                    });



                    tbodyTag.insertAdjacentHTML('afterbegin',str);
                
        

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function studentScore(stuNum){
 
    fetch('/stu/stuScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            stuNum : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        //조회된 데이터로 그림 그리기
        const detail_div =document.querySelector('.stu-detail-div');
       
        detail_div.innerHTML = '';
        
        let str =
        `
                        <div >
                            <div>
                                학생기본정보
                            </div>

                            <div>
                                <table>
                                    <thead>
                                        <tr>
                                            <td>학번</td>
                                            <td>소속반</td>
                                            <td>학생명</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="stuNumTd">${data.stuVO.stuNum}</td>
                                            <td>${data.stuVO.className}</td>
                                            <td>${data.stuVO.stuName}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    
                        <div>
                            <div>
                                학생 점수 정보
                            </div>
                            <div>
                                <table >
                                    <thead>
                                        <tr>
                                            <td>국어점수</td>
                                            <td>영어점수</td>
                                            <td>수학점수</td>
                                            <td>평균</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="scoreTd">${data.stuScoreVO== null?0: data.stuScoreVO.korScore}</td>
                                            <td class="scoreTd">${data.stuScoreVO== null?0: data.stuScoreVO.engScore}</td>
                                            <td class="scoreTd">${data.stuScoreVO== null?0: data.stuScoreVO.mathScore}</td>
                                            <td>${data.stuScoreVO== null?0.0: data.stuScoreVO.mathScore+
                                                data.stuScoreVO.engScore+
                                                data.stuScoreVO.korScore/3.0}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div>
                            <input type="button" value="점수입력" onclick="setInput()" id="btn" >
                            </div>
                        </div>
        `;
    
        detail_div.insertAdjacentHTML('afterbegin',str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function setInput(){

    const btn = document.querySelector("#btn")
    
    if(btn.value == '점수입력'){
        const tds =document.querySelectorAll('.scoreTd');
        // for(let i=0;i<tds.length;i++){
        //     tds[i].textContent = '<input type= "text>';
        // }
        // for(const e of tds){
        //     e.textContent = '<input type= "text>';
        // }
        tds.forEach(function(e , i){
            e.innerHTML = '<input type= "text" class="scoreInput">';
        });
        document.querySelector("#btn").value='저장';
    }
    else if(btn.value == '저장'){
        const result =confirm('입력한 정보로 점수를 등록할까요?');
        if(result){
            goScore();
        }
    }
}

function goScore(){
    const scoreNum =document.querySelectorAll(".scoreInput");
    const stuNum =document.querySelector(".stuNumTd").textContent;

    fetch('/stu/inScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
                'korScore' : scoreNum[0].value,
                'engScore' : scoreNum[1].value,
                'mathScore' :scoreNum[2].value,
                'stuNum' : stuNum
                
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('점수가 등록되었습니다.');
        studentScore(stuNum);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
