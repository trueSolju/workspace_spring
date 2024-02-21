
const updateItemCode=document.querySelector('#updateItemCode').value;

if(updateItemCode != 0){
    getDetail(updateItemCode);
}



// 상품목록 테이블의 행 클릭시 상품의 상세 정보 조회
function getDetail(itemCode){
// ------------------- 첫번째 방식 ---------------//
fetch('/admin/selectItemDetail', { //요청경로
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    //컨트롤러로 전달할 데이터
    body: new URLSearchParams({
       // 데이터명 : 데이터값
       'itemCode' : itemCode
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
    

    const detail_div= document.querySelector('.detail-div');
    detail_div.innerHTML='';
    
    let str ='';
    
    str+=`
    <form action="/admin/updateItemList" method="POST">
   
    <div class="row-mb-3">
            <div class="offset-1 col">
            <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
                <h3>상품 기본정보</h3>
            </div>
        </div>
        <div class="row-mb-3 text-start align-middle">
            <div class="offset-1 col">
                <div class="row-mb-3">
                    <div class="col">
                        <div class="row">
                            <div class="col-3 align-middle">
                                카테고리
                            </div>
                            
                           <div class="col-9">
                                    <select class="form-select" aria-label="Default select example" name="cateCode">`;
                                    data.cateList.forEach(function(cate,i){ 
                                        if(cate.cateCode==data.itemDetail.cateCode){
                                            str+=`<option value="${cate.cateCode}" selected >${cate.cateName}</option>`
                                        }
                                        else{
                                            str+=`<option value="${cate.cateCode}">${cate.cateName}</option>`
                                        }
                                        
                                    })
                    str+= ` </select>
                            </div>
                        </div>
                    </div>
                   <div class="col">
                        <div class="row">
                            <div class="col">
                                <div class="input-group input-group-sm mb-3">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">상품수량</span>
                                    <input type="text" class="form-control"
                                        aria-label="Sizing example input" name="itemStock"
                                        aria-describedby="inputGroup-sizing-sm" value=${data.itemDetail.itemStock}>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col">
                                <div class="input-group input-group-sm mb-3">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">상품명</span>
                                    <input type="text" class="form-control"
                                        aria-label="Sizing example input" name="itemName"
                                        aria-describedby="inputGroup-sizing-sm" value=${data.itemDetail.itemName}>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="row">
                            <div class="col-3">상품상태</div>
                            <div class="col-9">`;
                                   if(data.itemDetail.itemStatus==1){
                                        str+= `<input class="form-check-input" type="radio"
                                           name="itemStatus" id="inlineRadio1" value="1" checked>
                                           <label class="form-check-label" for="inlineRadio1">준비중</label>`
                                   }
                                   else{
                                    
                                    str+= `<input class="form-check-input" type="radio"
                                    name="itemStatus" id="inlineRadio1" value="1">
                                    <label class="form-check-label" for="inlineRadio1">준비중</label>`;
                                   }
                                   
                                    
                                   if(data.itemDetail.itemStatus==2){
                                        str+= `<input class="form-check-input" type="radio"
                                        name="itemStatus" id="inlineRadio1" value="2" checked>
                                        <label class="form-check-label" for="inlineRadio1">판매중</label>`
                                    }
                                    else{
                                        str+= `<input class="form-check-input" type="radio"
                                        name="itemStatus" id="inlineRadio1" value="2">
                                        <label class="form-check-label" for="inlineRadio1">판매중</label>`;
                                    }
                                    
                                    
                                    if(data.itemDetail.itemStatus==3){
                                        str+= `<input class="form-check-input" type="radio"
                                        name="itemStatus" id="inlineRadio1" value="3" checked>
                                        <label class="form-check-label" for="inlineRadio1">매진</label>`
                                    }
                                    else{
                                        str+= `<input class="form-check-input" type="radio"
                                        name="itemStatus" id="inlineRadio1" value="3">
                                        <label class="form-check-label" for="inlineRadio1">매진</label>`;
                                    }
                                        
                                    
                                    str+= `</div>
                           </div>
                        </div>
                    </div>
                </div>
                <div class="row-mt-2">
                    <div class="offset-1 col">
                        <h3>상품 이미지 정보</h3>

                    </div>
                </div>
                <div class="offset-1 row">
                    <div class="col-3">
                        메인이미지
                    </div>
                    <div class="col-9" >`;
                    data.itemDetail.imgList.forEach(function(img,i){
                        if(img.isMain == 'Y'){
                            str+=`<span class="pointer-span" onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>`;
                        }
                    })
                                str+= `</div>
                                    </div>
                                    <div class=" offset-1 row">
                                        <div class=" col-3">
                                            상세이미지
                                        </div>
                                        `;
                                            let cnt =0;
                                        data.itemDetail.imgList.forEach(function(img,i){
                                            if(img.isMain == 'N'){
                                                if(cnt==0){
                                                    str+=`
                                                    <div class="col-9">
                                                        <span class="pointer-span" onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                                    </div>
                                                    `;
                                                    cnt++;
                                                }
                                                else{
                                                    str+=`
                                                    <div class="offset-3 col-9">
                                                    <span class="pointer-span" onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                                    </div>
                                                    `;
                                                }
                                               
                                            }
                                        })
                                    str+=  `
                                    </div>
                                    <div class="row">
                                        <div class="offset-1 col-mt-3 text-start">
                                            <button type="submit" class="btn btn-info">변경</button>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        </form>
                        `;
    detail_div.insertAdjacentHTML('afterbegin',str )
    
})
//fetch 통신 실패 시 실행 영역
.catch(err=>{
    alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    console.log(err);
});
}
//이미지 모달창 띄우기

function showModal(attachedFileName){
    const img_modal = new bootstrap.Modal('#img-modal');

    const img_tag =document.querySelector('#img-modal img');

    img_tag.src=`/upload/${attachedFileName}`;

    img_modal.show();

}






