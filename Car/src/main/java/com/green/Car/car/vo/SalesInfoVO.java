package com.green.Car.car.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SalesInfoVO {
    private int CarNum;
    private String memberName;
    private String memberTel;
    private String carColor;
    private String carDate;
    private String modelNum;
    private CarInfoVO carInfo;

}

