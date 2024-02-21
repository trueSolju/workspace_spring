package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;

import java.util.List;

public interface MemberService {
    //회원가입
    void insertMember(MemberVO memberVO);
    MemberVO login(MemberVO memberVO);
}
