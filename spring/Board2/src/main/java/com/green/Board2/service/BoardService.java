package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;

import java.util.List;

public interface BoardService {
    int boardInsert(BoardVO boardVO);

    List<BoardVO> selectBoard(SearchVO searchVO);

    BoardVO selectOneBoard(BoardVO boardVO);

    void updateBoard(BoardVO boardVO);

    int deleteBoard(int boardNum);

    void updateCnt(int boardNum);

    List<BoardVO> selectSearch(BoardVO boardVO);

    //게시글 수 조회
    int selectBoardCnt();
}
