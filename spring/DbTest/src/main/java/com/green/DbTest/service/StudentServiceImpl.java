package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;
import org.springframework.stereotype.Service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service("stuService")
public class StudentServiceImpl implements StudentService{
    //Mybatis 에서 제공하는 데이터베이스 쿼리 기능이
    //정의 되어있는 객체
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    //디비작업 : 조회, 삽입, 삭제, 수정
    //삽입 : sqlSession.insert("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    //삭제 : sqlSession.delete("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    //수정 : sqlSession.update("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    //조회 : sqlSession.selectOne("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    //    : sqlSession.selectList("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    //selectOne : 조회 결과 데이터가 0행 혹은 1행일 경우.
    //selectList : 조회 할때마다 조회 되는 행의 개수가 매번 다른 경우.
    public void insertStudent() {
        sqlSession.insert("insertStudent");
    }

    @Override
    public void deleteStudent() {
        sqlSession.delete("deleteStudent");
    }

    @Override
    public void delete(int stuNo) {

        sqlSession.delete("delete",stuNo);
    }

    @Override
    public String selectName() {
        String name = sqlSession.selectOne("selectName");
        return name;
    }

    @Override
    public int selectScore() {
        int score = sqlSession.selectOne("selectScore");
        return score;
    }

    @Override
    public StudentVO selectStu() {
        StudentVO stu = sqlSession.selectOne("selectStu");
        return stu;
    }

    @Override
    public List<StudentVO> selectStuList() {
        List<StudentVO> list =sqlSession.selectList("selectStuList");
        return list;
    }
}
