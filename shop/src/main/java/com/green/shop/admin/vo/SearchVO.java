package com.green.shop.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
public class SearchVO {
    private String searchType;
    private String searchValue;
    private String fromDate;
    private String toDate;
}
