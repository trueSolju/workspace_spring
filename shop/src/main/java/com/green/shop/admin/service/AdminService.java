package com.green.shop.admin.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ItemVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {

    //상품 등록 및 이미지등록
    void itemInsert(ItemVO itemVO);



//    다음에 들어갈 ITEM_CODE조회
    int selectNextItemCode();

    //상품정보 변경 페이지
    List<ItemVO> selectItemBuy();

    ItemVO selectAdminUpdate(int itemCode);

    //상품정보 변경
    void updateItemList(ItemVO itemVO);
}
