package com.green.Board2.vo;

//페이지 정보를 담고 있는 클래스

public class PageVO {
    private int nowPage; // 현재 선택된 페이지 번호
    private int totalDataCnt; //전체 데이터수 (전체 게시글 수)
    private int displayDataCnt; //한 페이지에 보여지는 데이터 수
    private int displayPageCnt; //한 페이지에 보여주는 페이지수
    private int beginPage; // 화면에 보이는 첫번째 페이지 번호
    private int endPage; // 화면에 보이는 마지막 페이지 번호
    private boolean prev; //이전 버튼의 유무
    private boolean next; //다움 버튼의 유무

    public PageVO() {
        nowPage = 1;
        displayDataCnt = 5;
        displayPageCnt= 5;
    }

    public void setPageInfo(){
        //화면에 보이는 마지막 페이지 번호
        //올림(nowPage/displayPageCnt)*displayPageCnt
        endPage = (int)Math.ceil(nowPage/(double)displayPageCnt)*displayPageCnt;
        //화면에 보이는 첫번째 페이지 번호 셋팅
        beginPage = endPage- displayPageCnt+1;
        //전체 페이지 수
        int totalPageCnt= (int)Math.ceil(totalDataCnt/(double)displayDataCnt);

        //next 버튼의 유무
        if (totalPageCnt>endPage){
            next=true;
        }
        else {
            next= false;
            endPage = totalPageCnt;
        }
        //prev 버튼의 유무
        prev= beginPage==1 ? false : true;

    }
    public void setTotalDataCnt(int totalDataCnt){
        this.totalDataCnt = totalDataCnt;
    }
    public int getBeginPage(){
        return beginPage;
    }
    public int getEndPage(){
        return endPage;
    }
    public boolean getPrev(){
        return prev;
    }
    public boolean getNext(){
        return next;
    }
    public void setNowPage(int nowPage){
        this.nowPage=nowPage;
    }
    public int getDisplayDataCnt(){
        return displayDataCnt;
    }
    public int getNowPage(){
        return nowPage;
    }
}
