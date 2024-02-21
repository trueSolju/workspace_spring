package com.green.First.controller;

import com.green.First.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping ("regs")
    public String regMember(){
        return "reg";
    }
    //커맨드 객체 : 컨트롤러의 메소드의 매개변수 중에서
    //넘어오는 데이터를 받기 위해 선언한 객체.
    //커맨드 객체는 model을 사용하지 않아도 자동으로
    //html로 데이터가 전달 됨.
    //커맨드 객체가 자동으로 넘어갈때 넘어가는 데이터의 이름은
    //클래스명에서 앞글자만 소문자로 바꾼 이름으로 전달 됨.
    @PostMapping("detail")
    public String regMember_detail(Member member){
        System.out.println(member);
        return "reg_member_detail";
    }
    @PostMapping("goinfo")
    public String member_info(Member member){
        System.out.println(member);
        return "member_info";
    }
}
