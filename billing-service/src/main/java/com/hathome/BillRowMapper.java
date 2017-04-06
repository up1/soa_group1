package com.hathome;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Acer on 6/4/2560.
 */
public class BillRowMapper implements RowMapper<Bill> {
    @Override
    public Bill mapRow(ResultSet resultSet, int i) throws SQLException {
        Bill bill = new Bill();
        bill.setId(resultSet.getLong("id"));
        bill.setStatus(resultSet.getString("status"));
        bill.setAddress(resultSet.getString("address"));
        bill.setCartId(resultSet.getLong("cartId"));
        bill.setTotal(resultSet.getDouble("total"));
        return bill;
    }
}
