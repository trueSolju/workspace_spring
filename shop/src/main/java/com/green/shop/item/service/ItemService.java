package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.member.vo.MemberVO;

import java.util.List;

public interface ItemService {
    List<CategoryVO> cateSelect();
    List<ItemVO> itemSelect();
    ItemVO detailSelect(ItemVO itemVO);
}
