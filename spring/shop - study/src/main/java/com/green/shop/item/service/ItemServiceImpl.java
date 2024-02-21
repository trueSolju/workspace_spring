package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private SqlSessionTemplate session;

    @Override
    public List<CategoryVO> cateSelect() {

        return session.selectList("itemMapper.cateSelect");
    }
    @Override
    public List<ItemVO> itemSelect() {
        return session.selectList("itemMapper.itemSelect");
    }

    @Override
    public ItemVO detailSelect(ItemVO itemVO) {
        return session.selectOne("itemMapper.detailSelect",itemVO);
    }


}
