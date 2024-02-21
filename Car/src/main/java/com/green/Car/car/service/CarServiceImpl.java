package com.green.Car.car.service;

import com.green.Car.car.vo.CarInfoVO;
import org.apache.ibatis.jdbc.SQL;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{

    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public void insertCarInfo(CarInfoVO carInfoVO) {
        sqlSession.insert("carMapper.insertCarInfo",carInfoVO);
    }

    @Override
    public List<CarInfoVO> selectCarInfo() {
        return sqlSession.selectList("carMapper.selectCarInfo");
    }
}
