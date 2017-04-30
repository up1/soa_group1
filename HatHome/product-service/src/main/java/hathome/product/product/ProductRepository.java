package hathome.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return this.jdbcTemplate.queryForObject("SELECT id, name, detail, price, color, brand, amount, type FROM PRODUCT WHERE id=?", new Object[]{id}, new ProductRowMapper());
    }

    public List<Product> findProductsInPage(int page){
        int itemPerPage = 9;
        int firstItem = (page-1) * itemPerPage;
        return this.jdbcTemplate.query("SELECT id, name, detail, price, color, brand, amount, type FROM PRODUCT WHERE amount!=0 ORDER BY id LIMIT ?, ?", new Object[]{firstItem, itemPerPage}, new ProductRowMapper());
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