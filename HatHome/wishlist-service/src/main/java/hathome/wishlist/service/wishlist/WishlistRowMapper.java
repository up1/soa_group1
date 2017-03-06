package hathome.wishlist.service.wishlist;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Plern on 6/3/2560.
 */
public class WishlistRowMapper implements RowMapper<Wishlist> {

    public Wishlist mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlist_id(resultSet.getLong("wishlist_id"));
        wishlist.setUser_id(resultSet.getLong("user_id"));
        wishlist.setProduct_id(resultSet.getLong("product_id"));
        return wishlist;
    }
}
