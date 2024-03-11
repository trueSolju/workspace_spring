package com.green.Board.controller;

import com.green.Board.service.BoardService;
import com.green.Board.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class MemberController {
    @Resource(name = "boardService")
    private BoardService boardService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    //회원가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinForm(){

        return "join";
    }
    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO) {
        //비밀번호 암호화
        String encodePw= encoder.encode(memberVO.getMemberPw());
        memberVO.setMemberPw(encodePw);

        boardService.join(memberVO);
        return "redirect:/loginForm";
    }
    //로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(){

        return "login";
    }


    //1. spring 프로젝트 생성시 security를 사용하겠다는 dependency추가
    //2. 라이브러리를 추가하자 마자 인증 / 인가에 대해 프로세스가 자동으로 실행
    // 3. 기본 프로세스를 무조건 인증/인가를 받아야 되게끔 설계 되어 있음
    //4. 우리는 기본 인증 프로세스를 사용하지 않고,
    // 프로젝트에 맞게 인증/인가에 대해 프로세스를 구현
}
