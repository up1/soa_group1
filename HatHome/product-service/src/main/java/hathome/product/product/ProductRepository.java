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

    public List<Product> findRecentProduct(){
        return this.jdbcTemplate.query("SELECT id, name, detail, price, color, brand, amount, type, image FROM PRODUCT ORDER BY id DESC LIMIT 12;", new ProductRowMapper());
    }


}