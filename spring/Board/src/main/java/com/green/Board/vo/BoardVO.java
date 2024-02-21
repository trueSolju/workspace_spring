package com.green.Board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String writer;
    private String createDate;
    private int readCnt;
}
