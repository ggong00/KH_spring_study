package com.kh.myapp3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;  //상품번호
    private String pname;       //상품이름
    private Long quantity;   //수량
    private Long price;      //가격


}
