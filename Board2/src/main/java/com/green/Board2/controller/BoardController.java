package com.green.Board2.controller;

import com.green.Board2.service.BoardService;
import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;
    //게시판 목록화면
    @RequestMapping("/list")
    // SearchVO의 커맨드객체는 자동 데이터(html) 전달
    public String boardList(SearchVO searchVO, Model model){
        // 전체데이터수
        int totalDataCnt = boardService.selectBoardCnt();
        searchVO.setTotalDataCnt(totalDataCnt);

        //페이지 정보 세팅
        searchVO.setPageInfo();

        List<BoardVO> list =boardService.selectBoard(searchVO);
        model.addAttribute("boardList",list);
        System.out.println(searchVO);
        return "list";
    }
    //게시판글등록 페이지 가는코드
    @GetMapping("/goList")
    public String goboard(){
        return "board_write_form";
    }
    //게시판 글등록 시키고 목록으로 돌아가기

    @PostMapping("/write")
    public String boardInsert(BoardVO boardVO){
        boardService.boardInsert(boardVO);
        return "redirect:/board/list";
    }
    //게시판 제목 누르면 상세정보 페이지
    @GetMapping("/detail")
    public String boardselect(BoardVO boardVO, Model model){
        boardService.updateCnt(boardVO.getBoardNum());
        BoardVO vo = boardService.selectOneBoard(boardVO);
        model.addAttribute("boardInfo",vo);
        List<ReplyVO> list =replyService.selectListReply(boardVO);
        model.addAttribute("replyList",list);
        return "board_detail";
    }
    //게시글 삭제 하는 코드
    @GetMapping("/delete")
    public String boardDelete(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/board/list";
    }
    //게시글 수정 하는 화면
    @GetMapping("/updateForm")
    public String boardUpdateFrom(BoardVO boardVO, Model model){
        BoardVO result = boardService.selectOneBoard(boardVO);
        model.addAttribute("boardUp",result);
        return "update_form";
    }
    //게시글 수정
    @PostMapping("/update")
    public String boardUpdate(BoardVO boardVO){
        boardService.updateBoard(boardVO);
        return "redirect:/board/detail?boardNum="+boardVO.getBoardNum();
    }


    @GetMapping("writeForm")
    public String writeFrom(){
        return "write_form";
    }
    @PostMapping ("/writer")
    public String write(BoardVO boardVO, HttpSession session){
        //세션에 저장된 로그인한 유저의 아이디로 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());
        boardService.boardInsert(boardVO);
        return "redirect:/board/list";
    }

}
