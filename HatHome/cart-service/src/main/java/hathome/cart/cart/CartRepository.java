package hathome.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Mark on 3/6/2017.
 */
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cart> getItemInCart(){
        return this.jdbcTemplate.query("SELECT * FROM cart;", new CartRowMapper());
    }
}
