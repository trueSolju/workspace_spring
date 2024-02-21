package com.green.Board2.service;

import com.green.Board2.vo.MemberVO;

public interface MemberService {

    int insertMember(MemberVO memberVO);

    MemberVO login(MemberVO memberVO);
}
