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
        bill.setUserId(resultSet.getLong("userId"));
        bill.setAddress(resultSet.getString("address"));
        bill.setDate(resultSet.getString("date"));
        bill.setCartPrice(resultSet.getDouble("cartPrice"));
        bill.setShippingCost(resultSet.getDouble("shippingCost"));
        bill.setTotal(resultSet.getDouble("total"));
        return bill;
    }
}
