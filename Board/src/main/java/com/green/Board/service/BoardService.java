package com.green.Board.service;

import com.green.Board.vo.BoardVO;

import java.util.List;

public interface BoardService {

    int insertBoard(BoardVO boardVO);

    List<BoardVO> seleteList();

    BoardVO seleteDetail(int boardNum);

    int boardUpdate(BoardVO boardVO);

    int deleteBoard(BoardVO boardVO);
}
