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
        wishlist.setWishlistId(resultSet.getLong("wishlistId"));
        wishlist.setUserId(resultSet.getLong("userId"));
        wishlist.setProductId(resultSet.getLong("productId"));
        return wishlist;
    }
}
