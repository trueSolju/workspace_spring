package com.green.Car.car.service;

import com.green.Car.car.vo.CarInfoVO;
import com.green.Car.car.vo.SalesInfoVO;

import java.util.List;

public interface CarService {

    // 차모델 정보 저장
    void insertCarInfo(CarInfoVO carInfoVO);

    // 차모델 목록조회
    List<CarInfoVO> selectCarInfo();

    //멤버정보 저장
    void insetMember(SalesInfoVO salesInfoVO);
    //전체목록조회
    List<CarInfoVO> selectAllMember();
}
