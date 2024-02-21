package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.StuScoreVO;
import com.green.FetchStudent.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{

    @Autowired()
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ClassVO> selectClass() {
        return sqlSession.selectList("stuMapper.selectClass");
    }

    @Override
    public List<StuVO> selectStudent(StuVO stuVO) {

        return sqlSession.selectList("stuMapper.selectStudent", stuVO);
    }
    //학생 상세 정보
    @Override
    public StuVO selectOneStu(StuVO stuVO) {

        return sqlSession.selectOne("stuMapper.selectOneStu",stuVO);
    }
    //점수조회
    @Override
    public StuScoreVO selectScore(StuVO stuVO) {

        return sqlSession.selectOne("stuMapper.selectScore",stuVO);
    }

    @Override
    public void insertScore(StuScoreVO stuScoreVO) {
        sqlSession.insert("stuMapper.insertScore",stuScoreVO);
    }


}
