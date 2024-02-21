package com.green.shop.buy.vo;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BuyDetailVO {
    private int buyDetailCode;
    private  int itemCode;
    private int buyCnt;
    private int totalPrice;
    private int buyCode;
    private ItemVO itemVO;

}
