package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int boardInsert(BoardVO boardVO) {
        return sqlSession.insert("boardMapper.boardInsert",boardVO);
    }

    @Override
    public List<BoardVO> selectBoard(SearchVO searchVO) {

        return sqlSession.selectList("selectBoard", searchVO);
    }

    @Override
    public BoardVO selectOneBoard(BoardVO boardVO) {
        return sqlSession.selectOne("selectOneBoard",boardVO);
    }

    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }

    @Override
    public int deleteBoard(int boardNum) {
        return sqlSession.delete("boardMapper.deleteBoard",boardNum);
    }

    @Override
    public void updateCnt(int boardNum) {
        sqlSession.update("boardMapper.updateCnt",boardNum);
    }

    @Override
    public List<BoardVO> selectSearch(BoardVO boardVO) {
        return sqlSession.selectList("boardMapper.selectSearch",boardVO);
    }

    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("boardMapper.selectBoardCnt");
    }


}
