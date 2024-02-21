package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String cartList(Model model, HttpSession session){
        MemberVO loginInfo=(MemberVO) session.getAttribute("loginInfo");
        List<CartViewVO> cartList =cartService.cartViewSelect(loginInfo.getMemberId());
        //총가격을 계산후 html전달
        model.addAttribute("cartViewList",cartList);
        int sum=0;
        for (CartViewVO e : cartList){
            sum= sum+e.getTotalPrice();
        }
        //model.addAttribute("finalPrice",sum);
        return "content/cart/cart_list";
    }

    @GetMapping("/cartDelete")
    public String cartDelete(CartVO cartVO){
        cartService.cartDelete(cartVO);
        return "redirect:/cart/list";
    }
    @ResponseBody
    @PostMapping("/cartUpdate")
    public void update(CartVO cartVO){
        cartService.updateCart(cartVO);
    }
}
