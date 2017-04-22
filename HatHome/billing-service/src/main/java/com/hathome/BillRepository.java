package com.hathome;

import com.mysql.cj.api.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Acer on 6/4/2560.
 */

@Repository
public class BillRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public BillRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public Bill findById(long id){
        String sql = "SELECT id, user_id, address, date, cart_id, cart_cost, shipping_cost, total FROM BILLS WHERE id=?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new BillRowMapper());
    }

    @Transactional(readOnly = false)
    public BillStatus addBill(Bill bill){
        //call adapter
        BillStatus billStatus = new BillStatus();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO bills (user_id, address, date, cart_id, cart_cost, shipping_cost, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, bill.getUser_id());
                preparedStatement.setString(2, bill.getAddress());
                preparedStatement.setString(3, bill.getDate());
                preparedStatement.setLong(4, bill.getCart_id());
                preparedStatement.setDouble(5, bill.getCart_cost());
                preparedStatement.setDouble(6, bill.getShipping_cost());
                preparedStatement.setDouble(7, bill.getTotal());
                return preparedStatement;
            }
        }, keyHolder);
        billStatus.setStatus("success");
        billStatus.setId(keyHolder.getKey().longValue());
        EmailService.SendSimpleMessage();
        return billStatus;
    }
}
