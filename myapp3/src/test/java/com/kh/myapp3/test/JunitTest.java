package com.kh.myapp3.test;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.dao.ProductDAO;
import com.kh.myapp3.domain.svc.ProductSVC;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
// 테스트 메소드의 실행순서
// 1) 문자와 숫자 조합으로 되어있으면 숫자의 오름차순으로 실행
// 2) 클래스 레벨에 @TestMethodOrder, 메소드 레벨에 @Order로 결정
//

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitTest {

    @Autowired
    private ProductDAO productDAO;
    private static Product testProduct;

//
//    @BeforeAll
//    static void beforeAll() {
//        for (int i = 0; i < 3; i++) {
//            Product product = new Product();
//            product.setPname("상품" + i);
//            product.setPrice((i*100000L));
//            product.setQuantity((i*1000L));
//
//            productDAO.save(product);
//        }
//    }

    @Test
    @Order(1)
    @DisplayName("상품등록")
    void test1() {
        log.info("test1 called");
        Product product = new Product(null, "의자", 5L, 30000L);
        testProduct = productDAO.save(product);
        assertThat(testProduct).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("상품조회")
    void test2() {
        log.info("test2 called");
        Product findedProduct = productDAO.findById(testProduct.getProductId());
        assertThat(findedProduct).isEqualTo(testProduct);
    }

    @Test
    @Order(3)
    @DisplayName("상품수정")
    void test3() {
        log.info("test3 called");
        Long productId = testProduct.getProductId();
        Product product = new Product(testProduct.getProductId(), "책상", 7L, 70000L);
        productDAO.update(product);

        Product findedProduct = productDAO.findById(productId);
        assertThat(findedProduct).isEqualTo(product);
    }

    @Test
    @Order(4)
    @DisplayName("상품목록")
    @Disabled
    void test4() {
        log.info("test4 called");
        List<Product> aLl = productDAO.findALl();
        assertThat(aLl.size()).isEqualTo(1);
    }

    @Test
    @Order(5)
    @DisplayName("상품삭제")
    void test5() {
        log.info("test5 called");
        productDAO.delete(testProduct.getProductId());

        Product findedProduct = productDAO.findById(testProduct.getProductId());
        assertThat(findedProduct).isNull();
    }

//    @BeforeEach
//    void beforeEach() {
//        log.info("beforeEach called");
//    }
//
    @AfterEach
    void afterEach() {
        List<Product> aLl = productDAO.findALl();
        aLl.stream().forEach(ele -> {
            log.info("{}",ele);
        });
    }
}
