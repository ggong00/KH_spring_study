package com.kh.product.domain;

import java.util.List;

public interface ProductSVC {
    Long add(Product product);

    Integer del(Long productId);

    Integer update(Long productId, Product product);

    Product findById(Long productId);

    List<Product> findAll();
}
