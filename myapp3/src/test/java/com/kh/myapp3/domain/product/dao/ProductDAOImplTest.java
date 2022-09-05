package com.kh.myapp3.domain.product.dao;

import com.kh.myapp3.domain.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

    @Autowired
    ProductDAO productDAO;

    @Test
    @DisplayName("상품등록")
    void save() {
        Product product = new Product();
        product.setPname("피아노");
        product.setQuantity(1L);
        product.setPrice(250000L);

        Product savedProduct = productDAO.save(product);

        assertThat(savedProduct.getPname()).isEqualTo(product.getPname());
    }

    @Test
    @DisplayName("조회")
    void findById() {
        Long productId = 45L;
        Product findedProduct = productDAO.findById(productId);

        assertThat(findedProduct.getPname()).isEqualTo("마우스");
        assertThat(findedProduct.getPrice()).isEqualTo(10000L);
        assertThat(findedProduct.getQuantity()).isEqualTo(2L);
    }

    @Test
    @DisplayName("전체조회")
    void findAll() {
        List<Product> findedALl = productDAO.findALl();
        findedALl.stream().forEach(ele -> {
            log.info("상품: {}",ele);
        });

        assertThat(findedALl.size()).isEqualTo(13);
    }

    @Test
    @DisplayName("수정")
    void update() {
        Long productId = 45L;
        Product product = new Product();
        product.setProductId(productId);
        product.setPname("선풍기");
        product.setQuantity(1L);
        product.setPrice(123456L);

        productDAO.update(product);

        Product findedProduct = productDAO.findById(45L);

        assertThat(findedProduct.getPname()).isEqualTo(product.getPname());
        assertThat(findedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(findedProduct.getQuantity()).isEqualTo(product.getQuantity());
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        productDAO.delete(45L);

        Product findedProduct = productDAO.findById(45L);

        assertThat(findedProduct).isNull();
    }
}