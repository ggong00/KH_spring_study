package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {

    private final ProductDAO productDAO;

    //등록
    @Override
    public Integer save(Product product) {
        Integer product_id = productDAO.save(product);

        return product_id;
    }
}
