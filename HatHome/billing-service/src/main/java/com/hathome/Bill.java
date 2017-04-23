package com.hathome;

import com.hathome.adapter.Cart;
import com.hathome.adapter.Product;

import java.util.List;

/**
 * Created by Acer on 6/4/2560.
 */
public class Bill{
    private long id;
    private long user_id;
    private String username;
    private String address;
    private String date;
    private double cart_price;
    private double shipping_cost;
    private double total;
    private Cart cart;

    public Bill(long id, long user_id, String address, String date, long cart_id, double cart_price, double shipping_cost, double total, Cart cart) {
        this.id = id;
        this.user_id = user_id;
        this.address = address;
        this.date = date;
        this.cart_price = cart_price;
        this.shipping_cost = shipping_cost;
        this.total = total;
        this.cart = cart;
    }

    public Bill(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCart_price() {
        return cart_price;
    }

    public void setCart_price(double cart_price) {
        this.cart_price = cart_price;
    }

    public double getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(double shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}


