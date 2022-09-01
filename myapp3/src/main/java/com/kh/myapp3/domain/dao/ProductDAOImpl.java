package com.kh.myapp3.domain.dao;

import com.kh.myapp3.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor    //final 필드를 생성자의 매개변수로 해서 생성자를 만들어줌.
public class ProductDAOImpl implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    //등록
    @Override
    public Product save(Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into product values(product_product_id_seq.nextval,?,?,?)");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[]{"product_id"});
            pstmt.setString(1,product.getPname());
            pstmt.setLong(2,product.getQuantity());
            pstmt.setLong(3,product.getPrice());

            return pstmt;
        },keyHolder);
        Long product_id = Long.valueOf(keyHolder.getKeys().get("product_id").toString());

        product.setProductId(product_id);

        return product;
    }

    //조회
    @Override
    public Product findById(Long productId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select product_id,pname,quantity,price ");
        sql.append("from product ");
        sql.append("where product_id = ? ");

        Product product = null;

        try {
            product = jdbcTemplate.queryForObject(sql.toString(),
                        new BeanPropertyRowMapper<>(Product.class),
                        productId);
        } catch (EmptyResultDataAccessException e) {
            log.info("삭제대상 상품이 없습니다.");
        }

        return product;
    }

    //수정
    @Override
    public void update(Product product) {
        StringBuffer sql = new StringBuffer();
        sql.append(" update product ");
        sql.append("     set pname = ?, ");
        sql.append("         quantity = ?, ");
        sql.append("         price = ? ");
        sql.append("   where product_id = ? ");

        jdbcTemplate.update(
                sql.toString(),
                product.getPname(),
                product.getQuantity(),
                product.getPrice(),
                product.getProductId()
        );
    }

    //삭제
    @Override
    public void delete(Long productId) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from product where product_id = ?");

        jdbcTemplate.update(sql.toString(), productId);
    }

    //목록
    @Override
    public List<Product> findALl() {
        StringBuffer sql = new StringBuffer();
        sql.append("select product_id,pname,quantity,price from product ");

        //case1) 자동매핑 sql결과 레코드와 동일한 구조의 java객체가 존재할 경우
        List<Product> products = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Product.class));

        //case2) 수동매핑 sql결과 레코드와 걸렴명과 java객체의 멤버이름이 다른경우 or 타입이 다른경우
//        List<Product> products = jdbcTemplate.query(sql.toString(), new RowMapper<Product>() {
//            @Override
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Product product = new Product();
//                product.setProductId(rs.getLong("product_id"));
//                product.setPname(rs.getString("pname"));
//                product.setQuantity(rs.getLong("quantity"));
//                product.setPrice(rs.getLong("price"));
//                return product;
//            }
//        });
        return products;
    }

    //전체삭제
    @Override
    public void deleteAll() {
        String sql = "delete from product";
        int rows = jdbcTemplate.update(sql);
        log.info("삭제건수 {}",rows);
    }

    @Override
    public Long generatePid() {
        String sql = "select product_product_id_seq.currval from dual";
        Long newProductId = jdbcTemplate.queryForObject(sql, Long.class);

        return newProductId;
    }
}
