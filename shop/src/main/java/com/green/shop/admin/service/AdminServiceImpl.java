package com.green.shop.admin.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    private SqlSessionTemplate session;
    @Override
    //이 어노테이션이 붙어 있는 메소드 내부의 쿼리 실행은
    // 모든 쿼리가 실행 성공시 커밋!
    // 쿼리 중 하나라도 실패시 롤백!
    @Transactional(rollbackFor =  Exception.class)
    public void itemInsert(ItemVO itemVO) {

        session.insert("adminMapper.itemInsert",itemVO);
        session.insert("adminMapper.insertImgs",itemVO);
    }
    //상품등록이나 이미지 등록 중 에러나면 바로 롤백 하게 하는 어노테이션


    @Override
    public int selectNextItemCode() {
        return session.selectOne("adminMapper.selectNextItemCode");
    }

    //상품정보 변경
    @Override
    public List<ItemVO> selectItemBuy() {
        return session.selectList("adminMapper.selectItemBuy");
    }

    @Override
    public ItemVO selectAdminUpdate(int itemCode) {
        return session.selectOne("adminMapper.selectAdminUpdate",itemCode);
    }

    @Override
    public void updateItemList(ItemVO itemVO) {
        session.update("adminMapper.updateItemList", itemVO);
    }


}
