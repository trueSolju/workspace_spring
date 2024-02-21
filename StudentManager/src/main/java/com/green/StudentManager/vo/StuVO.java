package com.green.StudentManager.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StuVO {
    private int stuNo;
    private String stuName;
    private int korScore;
    private int engScore;
    private String intro;

}
