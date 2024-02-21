package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.ReplyVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
    @Autowired()
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertReply(ReplyVO replyVO) {
        return sqlSession.insert("replyMapper.insertReply",replyVO);
    }

    @Override
    public List<ReplyVO> selectListReply(BoardVO boardVO) {

        return sqlSession.selectList("replyMapper.selectListReply",boardVO);
    }

}
