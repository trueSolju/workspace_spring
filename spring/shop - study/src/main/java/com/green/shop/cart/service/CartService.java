package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {
    void cartInsert(CartVO cartVO);
    List<CartViewVO> cartViewSelect(String memberId);
    void cartDelete(CartVO cartVO);
    void updateCart(CartVO cartVO);
}
