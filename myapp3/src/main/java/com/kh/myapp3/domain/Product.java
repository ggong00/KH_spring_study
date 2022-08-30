package com.kh.myapp3.domain;

import lombok.Data;

@Data
public class Product {
    private Integer productId;  //상품번호
    private String pname;       //상품이름
    private Integer quantity;   //수량
    private Integer price;      //가격
}
