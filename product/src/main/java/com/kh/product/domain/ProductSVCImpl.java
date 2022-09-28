package com.kh.product.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {

    private final ProductDAO productDAO;

    @Override
    public Long add(Product product) {

        return productDAO.add(product);
    }

    @Override
    public Integer del(Long productId) {
        return productDAO.del(productId);
    }

    @Override
    public Integer update(Long productId, Product product) {
        return productDAO.update(productId,product);
    }

    @Override
    public Product findById(Long productId) {
        return productDAO.findById(productId);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }
}
