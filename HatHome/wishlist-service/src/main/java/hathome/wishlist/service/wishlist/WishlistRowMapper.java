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
        wishlist.setWishlistId(resultSet.getLong("wishlist_id"));
        wishlist.setUserId(resultSet.getLong("user_id"));
        wishlist.setProductId(resultSet.getLong("product_id"));
        return wishlist;
    }
}
