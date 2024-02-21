package com.green.Board.controller;

import com.green.Board.service.BoardServiceImpl;
import com.green.Board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @GetMapping("/")
    public String boardList(Model model){
        List<BoardVO> list =boardService.seleteList();
        //목록 데이터 조회 후 html전달
        model.addAttribute("boardList",list);
        System.out.println(list.size());
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

}
