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

    public List<Wishlist> findAllWishlist(){
        return this.jdbcTemplate.query("SELECT wishlist_id, user_id, product_id FROM WISHLIST", new WishlistRowMapper());
    }

    @Transactional(readOnly = true)
    public Wishlist findById(Long id) {
        String sql = "SELECT wishlist_id, user_id, product_id FROM WISHLIST WHERE wishlist_id=?";
        return this.jdbcTemplate.queryForObject(sql,
                new Object[]{id}, new WishlistRowMapper());
    }
}