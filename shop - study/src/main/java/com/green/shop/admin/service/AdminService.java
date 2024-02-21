package com.green.shop.admin.service;

import com.green.shop.item.vo.ItemVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {

    //상품 등록 및 이미지등록
    void itemInsert(ItemVO itemVO);



//    다음에 들어갈 ITEM_CODE조회
    int selectNextItemCode();
}
