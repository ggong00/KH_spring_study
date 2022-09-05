package com.kh.myapp3.domain.product.svc;

import com.kh.myapp3.domain.product.Product;

import java.util.List;

public interface ProductSVC {

    /**
     * 상품등록
     * @param product 상품정보
     * @return  상품아이디
     */
    Product save(Product product);

    /**
     * 조회
     * @param productId 상품아이디
     * @return 상품아이디
     */
    Product findById(Long productId);

    /**
     * 수정
     * @param product 상품정보
     */
    void update(Product product);

    /**
     * 삭제
     * @param productId 상품아이디
     */
    void delete(Long productId);

    /**
     * 전체조회
     * @return 상품전체
     */
    List<Product> findALl();

    /**
     * 전체삭제
     */
    void deleteAll();

    /**
     * 상품아이디생성
     *
     * @return
     */
    Long generatePid();
}
