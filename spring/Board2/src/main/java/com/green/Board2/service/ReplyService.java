package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.ReplyVO;

import java.util.List;

public interface ReplyService {
    int insertReply(ReplyVO replyVO);

    List<ReplyVO> selectListReply(BoardVO boardVO);

}
