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
        String sqlBill = "SELECT id, userId, address, date, cartPrice, shippingCost, total FROM bills WHERE id=?";
        Bill bill = this.jdbcTemplate.queryForObject(sqlBill, new Object[]{id}, new BillRowMapper());
        Cart cart = new Cart();

        //set username
        UserAdapter userAdapter = new UserAdapter();
        User user = userAdapter.getUserById(bill.getUserId());
        bill.setUsername(user.getEmail());

        //get product detail
        String sqlItem = "SELECT productId, amount FROM items WHERE billId=?";
        List<Item> items = this.jdbcTemplate.query(sqlItem, new Object[]{id}, new ItemRowMapper());
        List<Product> products = new ArrayList<>();
        for (Item item: items) {
            ProductAdapter productAdapter = new ProductAdapter();
            Product product = productAdapter.getProductById(item.getProductId());
            product.setAmount(item.getAmount());
            products.add(product);
        }
        cart.setProducts(products);
        bill.setCart(cart);
        return bill;
    }

    @Transactional(readOnly = false)
    public Bill addBill(BillStoreRequest request){

        Bill bill = new Bill();
        bill.setUserId(request.getUserId());

        //set user
        bill.setUsername(request.getUsername());
        bill.setAddress(request.getAddress());

        //set date
        bill.setDate(getCurrentDate());

        //set cart
        CartAdapter cartAdapter = new CartAdapter();
        bill.setCart(cartAdapter.getCartById(bill.getUserId()));

        //set price
        bill.setCartPrice(bill.getCart().getPrice());
        bill.setShippingCost(0);
        bill.setTotal(bill.getCartPrice() + bill.getShippingCost());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        //insert to bills
        String sqlBill = "INSERT INTO bills (userId, address, date, cartPrice, shippingCost, total) VALUES (?, ?, ?, ?, ?, ?)";
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlBill, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, bill.getUserId());
                preparedStatement.setString(2, bill.getAddress());
                preparedStatement.setString(3, bill.getDate());
                preparedStatement.setDouble(4, bill.getCartPrice());
                preparedStatement.setDouble(5, bill.getShippingCost());
                preparedStatement.setDouble(6, bill.getTotal());
                return preparedStatement;
            }
        }, keyHolder);

        bill.setId(keyHolder.getKey().longValue());

        //insert to items
        String sqlItem = "INSERT INTO items (billId, productId, amount) VALUES (?, ?, ?)";
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
        return bill;
    }

    private String getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new java.util.Date());

    }
}
