package com.green.shop.member.controller;

import com.green.shop.member.service.MemberServiceImpl;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;
    //회원가입
    @PostMapping("/join")
    public String head(MemberVO memberVO){
        //회원 가입 쿼리를 실행하는 메소드

        // 연락처 셋팅
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",","-"));
        //이메일 셋팅
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",",""));
        memberService.insertMember(memberVO);
        return "redirect:/item/list";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "content/member/login";
    }
    //로그인정보저장하는 메소드
    @PostMapping("/login")
    public String login(MemberVO memberVO,HttpSession session){
        //로그인 성공
        MemberVO loginInfo = (MemberVO) memberService.login(memberVO);
        if (loginInfo!=null){
            session.setAttribute("loginInfo",loginInfo);
        }
        return "content/member/login_result";
    }
    //비동기 로그인
    @ResponseBody
    @PostMapping("/loginFetch")
    public String loginFetch(MemberVO memberVO, HttpSession session) {
        MemberVO loginInfo = (MemberVO) memberService.login(memberVO);

        if (loginInfo != null) {
            session.setAttribute("loginInfo", loginInfo);
        }
        return loginInfo==null?"":loginInfo.getMemberId();
    }
    //로그아웃 메소드
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/item/list";
    }
}
