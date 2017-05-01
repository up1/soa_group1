package hathome.wishlist.service.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Plern on 6/3/2560.
 */
@Repository
public class WishlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wishlist> findAllWishlist(Long id){
        return this.jdbcTemplate.query("SELECT wishlist_id, user_id, product_id FROM WISHLIST WHERE user_id=?", new Object[]{id}, new WishlistRowMapper());
    }

    @Transactional
    public void addWishlist(Wishlist wishlist){
        String sql = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";
        this.jdbcTemplate.update(sql, wishlist.getUser_id(), wishlist.getProduct_id());
    }

    public void deleteWishlist(long product_id, long user_id){
        String sql = "DELETE FROM wishlist WHERE product_id = ? and user_id = ?";
        this.jdbcTemplate.update(sql, product_id, user_id);
    }
}
