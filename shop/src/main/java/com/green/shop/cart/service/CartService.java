package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    void cartInsert(CartVO cartVO);

    List<CartViewVO> cartViewSelect(String memberId);
//    장바구니 상품 삭제
    void cartDelete(CartVO cartVO);
//   장바구니 상품 수량 변경(cartVo, cartViewVo 둘다 상관 없음)
    void updateCart(CartVO cartVO);
// 장바구니 상품들 삭제
    void deleteCarts(CartVO cartVO);
    //장바구니에 담긴 상품 구매를 위한 장바구니 목록조회
    List<CartViewVO> selectCartListForBuy(CartVO cartVO);
}
