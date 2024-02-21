package com.green.shop.buy.controller;

import com.green.shop.buy.service.BuyServiceImpl;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.member.vo.MemberVO;
import groovy.transform.Sortable;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/buy")
public class BuyController {
    @Resource(name = "buyService")
    private BuyServiceImpl buyService;

    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    @GetMapping("/buyCarts")
    public String buyCarts(CartVO cartVO, HttpSession session){
        //구매 상세 테이블에 insert 할 ITEM_CODE, BUY_CNT, TOTAL_PRICE 를 조회
        List<CartViewVO> cartViewList =cartService.selectCartListForBuy(cartVO);
        //구매 정보 테이블에 들어갈 BUY PRICE(구매 총가격)
        int buyPrice=0;
        for (CartViewVO e:cartViewList){
            buyPrice = buyPrice+e.getTotalPrice();
        }
        //구매자 Id 들고오기
        MemberVO loginInfo= (MemberVO) session.getAttribute("loginInfo");
        String memberId =loginInfo.getMemberId();

        // 다음에 들어갈 BUY_CODE 결정
        int buyCode = buyService.selectNextBuyCode();

        //구매정보 및 구매 상세 테이블에 insert!
        BuyVO buyVO = new BuyVO();

        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(memberId);
        buyVO.setBuyPrice(buyPrice);
        List<BuyDetailVO> buyDetailList = new ArrayList<>();
        for (CartViewVO cartView :cartViewList){
            BuyDetailVO vo =new BuyDetailVO();
            vo.setItemCode(cartView.getItemCode());
            vo.setBuyCnt(cartView.getCartCnt());
            vo.setTotalPrice(cartView.getTotalPrice());
            buyDetailList.add(vo);
        }
        buyVO.setBuyDetailList(buyDetailList);

        buyService.insertBuys(buyVO,cartVO);

        return "redirect:/";
    }
    //바로구매
    @PostMapping("/buyRight")
    public String buyRight(BuyVO buyVO, BuyDetailVO buyDetail ,HttpSession session
                            ){

        MemberVO loginInfo= (MemberVO) session.getAttribute("loginInfo");

        int buyCode = buyService.selectNextBuyCode();


        buyVO.setBuyCode(buyCode);
        buyDetail.setBuyCode(buyCode);
        buyVO.setBuyPrice(buyDetail.getTotalPrice());
        buyVO.setMemberId(loginInfo.getMemberId());

        buyService.buyInsert(buyVO,buyDetail);

        return "redirect:/";
    }
//    구매 이력 페이지
    @GetMapping("/history")
//    required page란 이름으로 데이터가 넘어오긴 하는데 넘어와도 안넘어와도 문제는 없다
//    defaultValue 기본값으로 ~~를 주겠다.
    public String history(@RequestParam(name = "page",required = false, defaultValue = "history")String page ,
                          Model model, HttpSession session){

        MemberVO loginInfo =(MemberVO) session.getAttribute("loginInfo");
        //구매목록 조회
        List<BuyVO> buyList=buyService.selectBuyList(loginInfo.getMemberId());
        for (BuyVO e :buyList){

        System.out.println(e);
        }

        model.addAttribute("page",page);
        model.addAttribute("buyList",buyList);

        return "content/buy/history";
    }
}
