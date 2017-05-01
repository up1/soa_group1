package hathome.wishlist.service.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WishlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wishlist> findAllWishlist(Long id){
        return this.jdbcTemplate.query("SELECT wishlistId, userId, productId FROM WISHLIST WHERE userId=?", new Object[]{id}, new WishlistRowMapper());
    }

    @Transactional
    public void addWishlist(Wishlist wishlist){
        String sql = "INSERT INTO wishlist (userId, productId) VALUES (?, ?)";
        this.jdbcTemplate.update(sql, wishlist.getUserId(), wishlist.getProductId());
    }

    public void deleteWishlist(long productId, long userId){
        String sql = "DELETE FROM wishlist WHERE productId = ? and userId = ?";
        this.jdbcTemplate.update(sql, productId, userId);
    }
}
