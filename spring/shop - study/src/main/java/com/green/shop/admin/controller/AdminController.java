package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.Uploadutil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @GetMapping("/regItemForm")
    public String regItemForm(Model model) {
        //카테고리 목록조회
        model.addAttribute("categoryList", itemService.cateSelect());

        return "content/admin/reg_item_form";
    }

    //상품등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO
                          ,@RequestParam(name = "img") MultipartFile img
                        , @RequestParam(name = "imgs") MultipartFile[] imgs) {
       //메인이미지 하나 업로드
        ImgVO mainImgVO =Uploadutil.uploadFile(img);
       //상세이미지들 업로드
        List<ImgVO> imgList =Uploadutil.multiUploadFile(imgs);


        //////////////////////다음에 들어갈 ITEM_CODE 조회 /////////////////////////////////
        int itemCode =adminService.selectNextItemCode();

        //////////////상품등록쿼리실행/////////////////////
        itemVO.setItemCode(itemCode);


        ////////////////////상품 이미지 정보 등록 쿼리////////////////////////////////////////////////////////
        //itemVO에는 상품명, 가격, 상품소개, 카테코드가 들어있음.
        //이미지 등록시 채워줘야 하는 모든 데이터를 갖는 리스트 생성
        mainImgVO.setItemCode(itemCode);

        for(ImgVO subimg : imgList ){
            subimg.setItemCode(itemCode);
        }
        imgList.add(mainImgVO);
        itemVO.setImgList(imgList);

        adminService.itemInsert(itemVO);
        //System.out.println(itemVO);


        return "redirect:/admin/regItemForm";
    }
}
