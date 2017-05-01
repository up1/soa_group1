package com.hathome;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Acer on 23/4/2560.
 */
public class ItemRowMapper implements org.springframework.jdbc.core.RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setProductId(resultSet.getLong("productId"));
        item.setAmount(resultSet.getLong("amount"));
        return item;
    }
}

