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

    public List<Wishlist> findAllWishlist(){
        return this.jdbcTemplate.query("SELECT * FROM WISHLIST", new WishlistRowMapper());
    }
}
