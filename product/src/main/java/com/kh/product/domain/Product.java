package com.kh.product.domain;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String pname;
    private Integer count;
    private Integer price;
}
