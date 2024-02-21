package com.green.shop.item.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.item.service.ItemService;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//상품과 관련된 모든 페이지 이동 처리 컨트롤러
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("itemList",itemService.itemSelect());

        return "content/item/item_list";
    }
    @GetMapping("/detailForm")
    public String detailForm(ItemVO itemVO, Model model){
        ItemVO vo=itemService.detailSelect(itemVO);
        System.out.println(vo);
        model.addAttribute("item",vo);
        return "/content/item/item_detail";
    }


}
