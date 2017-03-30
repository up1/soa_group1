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

    public List<Product> searchProduct(String keyword, String price, String color, String brand, String type){
        String[] priceParts = price.split("-");
        Double start = Double.parseDouble(priceParts[0]);
        Double end = Double.parseDouble(priceParts[1]);

        return this.jdbcTemplate.query("SELECT id, name, detail, price, color, brand, amount, type, image FROM PRODUCT" +
                " WHERE (NAME LIKE '%" + keyword + "%'" +
                " OR DETAIL LIKE '%" + keyword + "%'" +
                " OR BRAND LIKE '%" + keyword + "%'" +
                " OR TYPE LIKE '%" + keyword + "%')" +
                " AND ((PRICE >= " + start + ") AND (PRICE <= " + end + "))" +
                " AND COLOR LIKE '%" + color + "%'" +
                " AND BRAND LIKE '%" + brand + "%'" +
                " AND TYPE LIKE '%" + type + "%'" +
                "ORDER BY ID DESC"
                , new ProductRowMapper());
    }

}