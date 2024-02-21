package com.green.shop.buy.service;

import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public int selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }
    @Override
//    exception= 어떤 오류가 나던 전부다 롤백 해버릴것이다.
    @Transactional(rollbackFor = Exception.class)
    public void insertBuys(BuyVO buyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetails", buyVO);
        sqlSession.delete("cartMapper.deleteCarts", cartVO);
    }

    @Override
//    @Transactional 이 select만 있으면 해줄 필요 없음
//    나머지 데이터일 경우만 저거 써줌.
    @Transactional(rollbackFor = Exception.class)
    public void buyInsert(BuyVO buyVO, BuyDetailVO buyDetailVO) {
        sqlSession.insert("buyMapper.insertBuy",buyVO);
        sqlSession.insert("buyMapper.insertDetail", buyDetailVO);
    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBuyList",memberId);
    }

    @Override
    public List<BuyVO> selectBuy(SearchVO searchVO) {
        return sqlSession.selectList("buyMapper.selectBuy",searchVO);
    }

    @Override
    public BuyVO adminBuy(int buyCode) {
        return sqlSession.selectOne("buyMapper.adminBuy",buyCode);
    }

}
