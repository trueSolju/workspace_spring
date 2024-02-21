package com.green.Board2.controller;


import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    //댓글 등록
    @PostMapping("/replyForm")
    public String replyInsert(ReplyVO replyVO, HttpSession session){

       MemberVO loginInfo =(MemberVO) session.getAttribute("loginInfo");
       replyVO.setWriter(loginInfo.getMemberId());

       replyService.insertReply(replyVO);
        return "redirect:/board/detail?boardNum="+replyVO.getBoardNum();
    }



}
