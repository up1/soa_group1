package com.hathome;

import com.hathome.adapter.*;
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
import java.util.ArrayList;
import java.util.List;

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
        String sqlBill = "SELECT id, user_id, address, date, cart_price, shipping_cost, total FROM BILLS WHERE id=?";
        Bill bill = this.jdbcTemplate.queryForObject(sqlBill, new Object[]{id}, new BillRowMapper());
        Cart cart = new Cart();

        //set username
        UserAdapter userAdapter = new UserAdapter();
        User user = userAdapter.getUserById(bill.getUser_id());
        bill.setUsername(user.getEmail());

        //get product detail
        String sqlItem = "SELECT product_id, amount FROM ITEMS WHERE bill_id=?";
        List<Item> items = this.jdbcTemplate.query(sqlItem, new Object[]{id}, new ItemRowMapper());
        List<Product> products = new ArrayList<>();
        for (Item item: items) {
            ProductAdapter productAdapter = new ProductAdapter();
            Product product = productAdapter.getProductById(item.getProduct_id());
            product.setAmount(item.getAmount());
            products.add(product);
        }
        cart.setProducts(products);
        bill.setCart(cart);
        return bill;
    }

    @Transactional(readOnly = false)
    public BillStatus addBill(BillStoreRequest request){

        Bill bill = new Bill();
        bill.setUser_id(request.getUser_id());

        //set user
        bill.setUsername(request.getUsername());
        bill.setAddress(request.getAddress());

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
        String sqlItem = "INSERT INTO items (bill_id, product_id, amount) VALUES (?, ?, ?)";
        for (Product product: bill.getCart().getProducts()) {
            this.jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlItem);
                    preparedStatement.setLong(1, bill.getId());
                    preparedStatement.setLong(2, product.getId());
                    preparedStatement.setLong(3, product.getAmount());
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
