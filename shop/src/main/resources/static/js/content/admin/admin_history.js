
//부트스트랩이 제공하는 모달 태그를 선택하는 방법
const buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');

//모달여기
//buy_detail_modal.show();

//모달 닫기
//buy_detail_modal.hide();

// 행 클릭시 구매 상세 내역 조회 및 모달창 띄우기
function showModal(buyCode) {

    fetch('/admin/selectBuyDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'buyCode': buyCode
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            

            document.querySelector('#modal-buyCode').textContent = data.buyCode;
            document.querySelector('#modal-memberId').textContent = data.memberId;
            document.querySelector('#modal-buyPrice').textContent = data.buyPrice;
            document.querySelector('#modal-buyDate').textContent = data.buyDate;

            const modal_tbody = document.querySelector('#modal-tbody');
            modal_tbody.innerHTML = '';

            let str ='';
            data.buyDetailList.forEach(function(buyDetail,i){
                        str +=
                    ` <tr>
                            <td>
                                <div class="row">
                                    <div class="col">
                                    ${data.buyDetailList.length-i}
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="row">
                                    <div class="offset-2 col text-start">
                                    <img width="70px" src="/upload/${buyDetail.itemVO.imgList[0].attachedFileName}">
                                    ${buyDetail.itemVO.itemName}
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="row">
                                    <div class="col">
                                    ${buyDetail.buyCnt}
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="row">
                                    <div class="col">
                                    ${buyDetail.totalPrice}
                                    </div>
                                </div>
                            </td>
                        </tr>
                                        
                    `;
                    
                });

                modal_tbody.insertAdjacentHTML("afterbegin", str)

            buy_detail_modal.show();
        })



        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}