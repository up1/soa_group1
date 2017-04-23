package com.hathome;

import com.hathome.adapter.CartAdapter;
import com.hathome.adapter.Product;
import com.hathome.adapter.User;
import com.hathome.adapter.UserAdapter;
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
import java.text.SimpleDateFormat;

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
        String sql = "SELECT id, user_id, address, date, cart_price, shipping_cost, total FROM BILLS WHERE id=?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new BillRowMapper());
    }

    @Transactional(readOnly = false)
    public BillStatus addBill(long userId){

        Bill bill = new Bill();
        bill.setUser_id(userId);

        //set user
        UserAdapter userAdapter = new UserAdapter();
        User user = userAdapter.getUserById(bill.getUser_id());
        bill.setUsername(user.getEmail());
        bill.setAddress(user.getAddress());

        //set date
        bill.setDate(getCurrentDate());

        //set cart
        CartAdapter cartAdapter = new CartAdapter();
        bill.setCart(cartAdapter.getCartById(bill.getUser_id()));

        //set price
        bill.setCart_price(bill.getCart().getPrice());
        bill.setShipping_cost(0);
        bill.setTotal(bill.getCart_price() + bill.getShipping_cost());

        BillStatus billStatus = new BillStatus();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //insert to bills
        String sqlBill = "INSERT INTO bills (user_id, address, date, cart_price, shipping_cost, total) VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlBill, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, bill.getUser_id());
                preparedStatement.setString(2, bill.getAddress());
                preparedStatement.setString(3, bill.getDate());
                preparedStatement.setDouble(4, bill.getCart_price());
                preparedStatement.setDouble(5, bill.getShipping_cost());
                preparedStatement.setDouble(6, bill.getTotal());
                return preparedStatement;
            }
        }, keyHolder);

        bill.setId(keyHolder.getKey().longValue());

        //insert to items
        String sqlItem = "INSERT INTO items (bill_id, product_id) VALUES (?, ?)";
        for (Product product: bill.getCart().getProducts()) {
            this.jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlItem);
                    preparedStatement.setLong(1, bill.getId());
                    preparedStatement.setLong(2, product.getId());
                    return preparedStatement;
                }
            });

        }

        billStatus.setStatus("success");
        billStatus.setId(bill.getId());
        EmailService.SendSimpleMessage();
        return billStatus;
    }

    private String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new java.util.Date());

    }
}
