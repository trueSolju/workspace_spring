package com.green.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemVO {
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private String itemIntro;
    private String regDate;
    private int cateCode;
    private int itemStatus;
    private String strStatus;
    private List<ImgVO> imgList;
    private CategoryVO categoryVO;

}
