package hathome.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mark on 3/6/2017.
 */
@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cart> getUnpaidItemInCart() {
        return this.jdbcTemplate.query(
                "SELECT * FROM cart WHERE status = 'unpaid';"
                , new CartRowMapper());
    }

    public List<Cart> getItemInCartByUserId(Long userId) {
        return this.jdbcTemplate.query(
                "SELECT * FROM cart " + "WHERE user_id = " + userId + " AND status = 'unpaid';"
                , new CartRowMapper());
    }

    public void updateAmount(Cart cartItem) {
        if (cartItem.getAmount() > 0) {
            String updateSqlString = "UPDATE cart SET amount = ? WHERE id = ? AND status = 'unpaid'";
            this.jdbcTemplate.update(updateSqlString, cartItem.getAmount(), cartItem.getId());
        } else {
            removeProduct(cartItem);
        }
    }

    @Transactional
    public void addProduct(Cart cartItem) {
        String addSqlString = "INSERT INTO cart (user_id, product_id) VALUES (?, ?)";
        this.jdbcTemplate.update(addSqlString, cartItem.getUser_id(), cartItem.getProduct_id());
    }

    public void removeProduct(Cart cartItem) {
        String removeSqlString = "DELETE FROM cart WHERE id = ? AND status = 'unpaid'";
        this.jdbcTemplate.update(removeSqlString, cartItem.getId());
    }

    public void updateMultiple(List<Cart> cartList) {
        String sql = "UPDATE cart SET amount = CASE id ";
        String idList = "";
        for (Cart item : cartList) {
            sql += ("WHEN " + item.getId() + " THEN " + item.getAmount() + " ");
            idList += item.getId() + ",";
        }
        idList = idList.substring(0, idList.length() - 1);
        sql += " END  WHERE id IN (" + idList + ")";
        this.jdbcTemplate.update(sql);
    }

    public void checkOut(Long userId) {
        String sql = "UPDATE cart SET status = 'paid' WHERE user_id = ? AND status = 'unpaid'";
        this.jdbcTemplate.update(sql, userId);
    }
}
