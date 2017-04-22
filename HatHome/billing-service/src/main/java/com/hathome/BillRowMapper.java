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
        bill.setUser_id(resultSet.getLong("user_id"));
        bill.setAddress(resultSet.getString("address"));
        bill.setDate(resultSet.getString("date"));
        bill.setCart_id(resultSet.getLong("cart_id"));
        bill.setCart_cost(resultSet.getDouble("cart_cost"));
        bill.setShipping_cost(resultSet.getDouble("shipping_cost"));
        bill.setTotal(resultSet.getDouble("total"));
        return bill;
    }
}
