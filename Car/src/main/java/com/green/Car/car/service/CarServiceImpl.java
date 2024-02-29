package com.green.Car.car.service;

import com.green.Car.car.vo.CarInfoVO;
import com.green.Car.car.vo.SalesInfoVO;
import org.apache.ibatis.jdbc.SQL;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{

    //자동차 정보 저장
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public void insertCarInfo(CarInfoVO carInfoVO) {
        sqlSession.insert("carMapper.insertCarInfo",carInfoVO);
    }

    // 자동차 정보 조회
    @Override
    public List<CarInfoVO> selectCarInfo() {
        return sqlSession.selectList("carMapper.selectCarInfo");
    }

    //멤버 정보저장
    @Override
    public void insetMember(SalesInfoVO salesInfoVO) {
        sqlSession.insert("carMapper.insetMember", salesInfoVO);
    }
    //정보 전체 조회
    @Override
    public List<CarInfoVO> selectAllMember() {
        return sqlSession.selectList("carMapper.selectAllMember");
    }
}
