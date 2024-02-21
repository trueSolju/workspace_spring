package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {

    //SELECT 리턴 : 매번 바뀜.
    //INSERT, DELETE, UPDATE : void, int(정석)

    //리턴타입 : 쿼리 실행 결과를 받아옴.
    //insert, update, delete는 쿼리 실행 결과가
    //몇개의 행이 삽입, 삭제, 수정 되었는지는 보여줌
    //위 세개의 쿼리 결과 리턴 타입은 무조건 int, void
    //selete는 어떤 쿼리 인지에 따라 리턴타입이 달라짐.
    //조회결과 데이터가 0행 혹은 1행 : vo클래스
    //조회결과가 데이터 행의 갯수가 매번 다르다 : List<VO>

    //매개변수 : 쿼리 실행 시 빈값을 채우는 역할
    //빈 값을 채울 데이터가 0개, 1개, 여러개로 구분.
    //빈 값이 0 개 : 매개변수가 필요없음.
    //빈 값이 1 개 :
    //1) 빈 값의 자료형이 숫자인 경우
    // 매개변수로 int 자료형 하나
    //2) 빈 값의 자료형이 문자열인 경우
    // 매개변수로 String 자료형 하나
    //빈값이 여러개인 경우는 vo객체

    //학생등록
    int insertStu(StuVO stuVO);

    List<StuVO> selectStuList();

    StuVO selectStu(int stuNo);

    int deleteStu(int stuNo);
}

