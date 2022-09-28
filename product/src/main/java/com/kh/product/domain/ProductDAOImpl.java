package com.kh.product.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductDAOImpl implements ProductDAO{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long add(Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into product ");
        sql.append(" values(product_product_id_seq.nextval,?,?,?) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql.toString(), new String[]{"product_id"});
            ps.setString(1, product.getPname());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getCount());

            return ps;
        },keyHolder);

        return Long.valueOf(keyHolder.getKeys().get("product_id").toString());
    }

    @Override
    public Integer del(Long productId) {
        String sql = "delete product where product_id = ? ";

        return jdbcTemplate.update(sql,productId);
    }

    @Override
    public Integer update(Long productId, Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append("  update product set pname = ?, ");
        sql.append("          price = ?, ");
        sql.append("          count = ? ");
        sql.append("  where product_id = ? ");

        return jdbcTemplate.update(
                sql.toString(),
                product.getPname(),
                product.getPrice(),
                product.getCount(),
                productId

        );
    }

    @Override
    public Product findById(Long productId) {
        String sql = "select * from product where product_id = ? ";

        Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), productId);

        return product;
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from product ";

        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        return products;
    }
}
