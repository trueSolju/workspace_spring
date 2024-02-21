package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.service.BuyServiceImpl;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.member.vo.MemberVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.Uploadutil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @Resource(name = "buyService")
    private BuyServiceImpl buyService;

    @GetMapping("/regItemForm")
    public String regItemForm(Model model) {
        //카테고리 목록조회
        model.addAttribute("categoryList", itemService.cateSelect());
        model.addAttribute("page",2);
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
//    관리자 구매 목록
    @RequestMapping("/adminHistory")
    public String adminHistory(Model model, SearchVO searchVO){
        model.addAttribute("buyList",buyService.selectBuy(searchVO));
        System.out.println(searchVO);
        model.addAttribute("page",1);
        return "content/admin/admin_history";
    }


    @PostMapping("/selectBuyDetail")
    @ResponseBody
    public BuyVO buyList(@RequestParam(name = "buyCode")int buyCode){
        //구매상세내역 조회

        BuyVO buyVO =buyService.adminBuy(buyCode);


        return buyVO;
    }
//    상품정보 변경 페이지
    @GetMapping("/itemBuy")
    public String itemBuy(Model model, @RequestParam(name = "itemCode", required = false, defaultValue = "0")int itemCode){
        model.addAttribute("itemList",adminService.selectItemBuy());
        model.addAttribute("page",4);
        model.addAttribute("updateItemCode",itemCode);
        return "content/admin/update_item";
    }
    //상품 정보 변경 화면의 상품 목록 테이블의 행 클릭시
    //상품의 상세 정보를 조회하는 비동기
    @PostMapping("/selectItemDetail")
    @ResponseBody
    public Map<String, Object> selectItemDetail(@RequestParam(name = "itemCode")int itemCode){
//        상품상세 정보
        ItemVO itemDetail=adminService.selectAdminUpdate(itemCode);
        //카테고리 목록조회
        List<CategoryVO> cateList=itemService.cateSelect();

        //위 두 데이터를 담을 수 있는 map 객체 생성
        Map<String, Object> map = new HashMap<>();
        map.put("itemDetail",itemDetail);
        map.put("cateList",cateList);

        return map;

    }
    @PostMapping("/updateItemList")
    public String updateItemList(ItemVO itemVO){
        adminService.updateItemList(itemVO);
        return "redirect:/admin/itemBuy?itemCode="+itemVO.getItemCode();
    }
}
