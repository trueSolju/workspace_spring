package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    //장바구니 상품등록
    @ResponseBody
    @PostMapping("/cartInsert")
    public void cartInsert(CartVO cartVO, HttpSession session){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        cartService.cartInsert(cartVO);
    }
    //장바구니 목록 조회
    @GetMapping("/list")
    public String cartList(Model model, HttpSession session,
                           @RequestParam(name = "page", required = false, defaultValue = "cartList")String page){
        MemberVO loginInfo=(MemberVO) session.getAttribute("loginInfo");
        List<CartViewVO> cartList =cartService.cartViewSelect(loginInfo.getMemberId());
        //총가격을 계산후 html전달
        model.addAttribute("cartViewList",cartList);
        int sum=0;
        for (CartViewVO e : cartList){
            sum= sum+e.getTotalPrice();
        }
        model.addAttribute("page", page);
        //model.addAttribute("finalPrice",sum);
        return "content/cart/cart_list";
    }

    //장바구니 상품 삭제
    @GetMapping("/cartDelete")
    public String cartDelete(CartVO cartVO){
        cartService.cartDelete(cartVO);
        return "redirect:/cart/list";
    }
    //장바구니 상품 수량 변경
    @ResponseBody
    @PostMapping("/cartUpdate")
    public void update(CartVO cartVO){

        cartService.updateCart(cartVO);

    }
    //선택삭제 하는 쿼리
    @GetMapping("/deleteCarts")
    public String deleteCarts(CartVO cartVO){
        //System.out.println(Arrays.toString(cartCodes));

        cartService.deleteCarts(cartVO);
        return "redirect:/cart/list";
    }
}
