package com.green.Board.service;

import com.green.Board.vo.BoardVO;
import com.green.Board.vo.MemberVO;

import java.util.List;

public interface BoardService {

    int insertBoard(BoardVO boardVO);

    List<BoardVO> seleteList();

    BoardVO seleteDetail(int boardNum);

    int boardUpdate(BoardVO boardVO);

    int deleteBoard(BoardVO boardVO);

    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(String memberId);

}
