package com.kh.product.web;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String pname;
    private Integer count;
    private Integer price;
}
