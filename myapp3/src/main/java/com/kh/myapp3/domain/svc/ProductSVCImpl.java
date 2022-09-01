package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {

    private final ProductDAO productDAO;

    //등록
    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    //조회
    @Override
    public Product findById(Long productId) {

        return productDAO.findById(productId);
    }

    //수정
    @Override
    public void update(Product product) {
        productDAO.update(product);

    }

    //삭제
    @Override
    public void delete(Long productId) {
        productDAO.delete(productId);
    }

    //목록
    @Override
    public List<Product> findALl() {
        return productDAO.findALl();
    }

    //전체삭제
    @Override
    public void deleteAll() {
        productDAO.deleteAll();
    }

    //시퀀스값 조회
    @Override
    public Long generatePid() {
        return productDAO.generatePid();
    }
}
