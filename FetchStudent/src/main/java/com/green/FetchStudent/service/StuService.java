package com.green.FetchStudent.service;


import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.StuScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {

    List<ClassVO> selectClass();

    //학생목록조회
    List<StuVO> selectStudent(StuVO stuVO);

    //학생상세정보조회
    StuVO selectOneStu(StuVO stuVO);
    //점수조회
    StuScoreVO selectScore(StuVO stuVO);

    //점수등록
    void insertScore(StuScoreVO stuScoreVO);
}
