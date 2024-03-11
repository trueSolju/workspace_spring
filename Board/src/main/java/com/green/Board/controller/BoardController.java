package com.green.Board.controller;

import com.green.Board.service.BoardServiceImpl;
import com.green.Board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    //게시글 목록 페이지로 이동
    @GetMapping("/")
    public String boardList(Model model){
        List<BoardVO> list =boardService.seleteList();
        //목록 데이터 조회 후 html전달
        model.addAttribute("boardList",list);
        System.out.println(list.size());

        // 암호화 예제
        // enode : 매개변수로 전달된 문자열을 암호화
        String s1 = encoder.encode("java");
        String s2 = encoder.encode("java");
        System.out.println(s1);
        System.out.println(s2);

        //암호화 데이터를 기준 데이터
        boolean b1 = encoder.matches("java", s1);
        System.out.println(b1);

        return "board_list";
    }
    //글쓰기 페이지로 이동
    @GetMapping("/write")
    public String write(){
        return "board_write_form";
    }
    //글 작성등록 화면
    @PostMapping("/write")
    public String writeFrom(BoardVO boardVO){
        boardService.insertBoard(boardVO);
        return "redirect:/";
    }
    //제목누르면 페이지 이동 메소드+상세조회
    @GetMapping("/detail")
    public String detail(BoardVO boardVO, Model model){
        BoardVO board = boardService.seleteDetail(boardVO.getBoardNum());
        model.addAttribute("boardInfo",board);
        return "board_detail";
    }
    //수정페이지 이동후 조회한 값 넣어주기
    @GetMapping("/update")
    public String update(@RequestParam(name = "boardNum") int boardNum, Model model){
        BoardVO board = boardService.seleteDetail(boardNum);
        model.addAttribute("boardUp",board);

        return "update_form";
    }
    //값수정후 상세페이지
    @PostMapping("/update")
    public String updateForm(BoardVO boardVO){
        boardService.boardUpdate(boardVO);
        return "redirect:/detail?boardNum="+boardVO.getBoardNum();
    }
    //글 삭제하기
    @GetMapping("/delete")
    public String delete(BoardVO boardVO){
        boardService.deleteBoard(boardVO);
        return "redirect:/";
    }
    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/deny")
    public String deny(){
        return "deny";
    }

    @GetMapping("/sample")
    public String sample(){
        return "security_sample";
    }
    @GetMapping("/sec")
    public String securitySample(Authentication authentication){
        //1. 로그인 정보를 받아오기 위해서 매개변수에  Authentication 자료형에 추가
        //2. 로그인 정보 받아오기
        User user =(User) authentication.getPrincipal();

        //로그인한 회원의 아이디
        System.out.println(user.getUsername());

        //로그인한 회원의 비밀번호
        System.out.println(user.getPassword());

        //로그인한 회원의 권한 정보
        List<GrantedAuthority> authlist = new ArrayList<>(user.getAuthorities());
        for (GrantedAuthority list:authlist){
            System.out.println(list.getAuthority());
        }


        return "redirect:/";
    }
}
