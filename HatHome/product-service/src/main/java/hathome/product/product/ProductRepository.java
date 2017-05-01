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
        return this.jdbcTemplate.query("SELECT id, name, detail, price, color, brand, amount, type FROM PRODUCT WHERE amount!= 0 ORDER BY id DESC LIMIT 12;", new ProductRowMapper());
    }

    public List<Product> findAllProduct(){
        return this.jdbcTemplate.query("SELECT id, name, detail, price, color, brand, amount, type FROM PRODUCT WHERE amount!= 0", new ProductRowMapper());
    }

    public Product findProductById(Long id){
        try {
            return this.jdbcTemplate.queryForObject("SELECT id, name, detail, price, color, brand, amount, type FROM PRODUCT WHERE id=?", new Object[]{id}, new ProductRowMapper());
        }catch (Exception exception){
            throw new ProductNotFoundException(id);
        }
    }


    public List<Product> searchProduct(String keyword){

        return this.jdbcTemplate.query("SELECT id, name, detail, price, color, brand, amount, type FROM PRODUCT" +
                " WHERE (NAME LIKE '%" + keyword + "%'" +
                " OR DETAIL LIKE '%" + keyword + "%'" +
                " OR BRAND LIKE '%" + keyword + "%'" +
                " OR TYPE LIKE '%" + keyword + "%')" +
                "ORDER BY ID DESC"
                , new ProductRowMapper());
    }

}