package com.green.Board2.controller;

import com.green.Board2.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionTestController {


    @GetMapping("/page1")
    public String page1(HttpSession session){
        //세션에 데이터 저장하기
        session.setAttribute("name","java");
        session.setAttribute("age",20);
        session.setAttribute("member",new MemberVO());
        //세션 유지시간 설정 : 초단위
        session.setMaxInactiveInterval(60*30);
        return "page1";
    }
    //웹브라운져가 커져있는동안은 세션이 유지
    //다른 웹브라우져끼리는 세션이 공유안됌
    // 컨트롤러가 런이 안되면 세션이 안됌
    @GetMapping("/page2")
    public String page2(HttpSession session){
        //세션에 담긴 데이터 확인
        Object name = (String)session.getAttribute("name");
        Object age= (int)session.getAttribute("age");
        Object member = (MemberVO)session.getAttribute("member");
        //세션 데이터 삭제
        session.removeAttribute("age");
        //모든 세션 데이터 지우기
        session.invalidate();
        return "page2";
    }
    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }
}
