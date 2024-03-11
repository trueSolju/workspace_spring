package com.green.Board.vo;

import lombok.*;

//생성자를 구현하는 어노테이션
// final(상수, 값 변경 안됌)
//@NoArgsConstructor(매개변수가 없는 기본생성자)
//@AllArgsConstructor(멤버변수 모두를 매개변수로 받는 생성자)
//@RequiredArgsConstructor(필수매개변수만 생성자로 만듦/final을 쓰면 매개변수가
// 생기는데 상수는 한번 값이 정해지면 변경되지 않기 때문에 생성자로 기본값을 정해줘야함)
//@Data (기본생성자,get,set,to)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRoll;
}

class BuilderTest{
    public void test(){
        //id를 java라는 초기값으로 갖는 객체 생성
        MemberVO v1 = MemberVO.builder()
                            .memberId("java")
                            .build();

        MemberVO v2 = MemberVO.builder()
                            .memberId("java")
                            .memberName("hong")
                            .build();

        MemberVO v3 =MemberVO.builder()
                            .memberName("hong")
                            .memberPw("1111")
                            .memberRoll("USER")
                            .build();

        //기본생성자
        MemberVO v4 =MemberVO.builder().build();

    }
}