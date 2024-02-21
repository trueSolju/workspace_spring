package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MapDataVO {
    private int cartCode;
    private MapdataMember memberInfo;
    private ItemInfo itemInfo;
}
@Setter
@Getter
@ToString
class MapdataMember{
    private String memberId;
    private  String memberName;
}
@Setter
@Getter
@ToString
class ItemInfo{
    private int itemCode;
    private String itemName;
    private  int itemPrice;
    private List<ImgInfo> imgList;
}
@Setter
@Getter
@ToString
class ImgInfo{
    private String imgName;
    private int imgCode;
}
