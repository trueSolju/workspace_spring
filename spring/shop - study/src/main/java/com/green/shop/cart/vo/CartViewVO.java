package com.green.shop.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartViewVO {
    private int cartCode;
    private int itemCode;
    private String memberId;
    private int cartCnt;
    private String cartDate;
    private String itemName;
    private int itemPrice;
    private String itemIntro;
    private int totalPrice;
    private String memberName;
    private String memberTel;
    private String address;
    private String attachedFileName;
    private String originFileName;
    private String isMain;
    private int imgCode;
    private int cateCode;
    private String cateName;




}
