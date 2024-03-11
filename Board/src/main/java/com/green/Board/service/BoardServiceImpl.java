package com.green.Board.service;

import com.green.Board.vo.BoardVO;
import com.green.Board.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public int insertBoard(BoardVO boardVO) {

        return sqlSession.insert("boardMapper.insertBoard",boardVO);
    }

    @Override
    public List<BoardVO> seleteList() {
        return sqlSession.selectList("boardMapper.selectBoard");
    }

    @Override
    public BoardVO seleteDetail(int boardNum) {

        return sqlSession.selectOne("boardMapper.selectBoardDate",boardNum);
    }

    @Override
    public int boardUpdate(BoardVO boardVO) {
        return sqlSession.update("boardMapper.boardUpdate",boardVO);
    }

    @Override
    public int deleteBoard(BoardVO boardVO) {
        return sqlSession.delete("boardMapper.deleteBoard",boardVO);
    }

    @Override
    public void join(MemberVO memberVO) {
        sqlSession.insert("memberMapper.join",memberVO);
    }

    @Override
    public MemberVO login(String memberId) {
        return sqlSession.selectOne("memberMapper.login",memberId);
    }
}
