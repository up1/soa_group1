package hathome.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mark on 3/6/2017.
 */
@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cart> getItemInCart(){
        return this.jdbcTemplate.query("SELECT * FROM cart;", new CartRowMapper());
    }

    public List<Cart> getItemInCartById(String userId) {
        return this.jdbcTemplate.query("SELECT () FROM cart " +
                "WHERE user_id = " + userId + " AND status = 'unpaid' ;"
                , new CartRowMapper());
    }

    public void updateAmount(String productId, BigDecimal amount) {
        this.jdbcTemplate.query("UPDATE cart SET amount = " + amount
                + " WHERE product_id = " + productId + ";"
                ,new CartRowMapper());
    }
}
