package hathome.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> findAllProduct(){
        return this.jdbcTemplate.query("SELECT * FROM PRODUCT;", new ProductRowMapper());
    }

    public List<Product> searchProduct(String keyword){
        return this.jdbcTemplate.query("SELECT * FROM PRODUCT" +
                " WHERE NAME LIKE '%" + keyword + "%'" +
                " OR DETAIL LIKE '%" + keyword + "%'" +
                " OR BRAND LIKE '%" + keyword + "%'" +
                " OR TYPE LIKE '%" + keyword + "%'", new ProductRowMapper());
    }

}