package com.green.Car.car.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CarInfoVO {
    private int modelNum;
    private String modelName;
    private int carPrice;
    private String carCompany;

}
