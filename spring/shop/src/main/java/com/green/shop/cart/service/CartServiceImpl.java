package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLPermission;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void cartInsert(CartVO cartVO) {

        // 현재 내 장바구니에  추가 하려는 상품이 있는지 확인.
        int cnt = sqlSession.selectOne("cartMapper.cartSelect",cartVO);
        //존재하지 않으면
        if (cnt==0){
        sqlSession.insert("cartMapper.cartInsert", cartVO);

        }
        //존재하면 수량만 변경
        else {
            sqlSession.update("cartMapper.plusCartUpdate",cartVO);
        }
    }

    @Override
    public List<CartViewVO> cartViewSelect(String memberId) {
        return sqlSession.selectList("cartMapper.cartViewSelect",memberId);
    }
// 장바구니 상품삭제
    @Override
    public void cartDelete(CartVO cartVO) {
        sqlSession.selectOne("cartMapper.cartDelete",cartVO);
    }
//장바구니 상품 수정
    @Override
    public void updateCart(CartVO cartVO) {
        sqlSession.update("cartMapper.updateCart",cartVO);
    }

    @Override
    public void deleteCarts(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteCarts", cartVO);
    }

    @Override
    public List<CartViewVO> selectCartListForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.selectCartListForBuy",cartVO);
    }


}
