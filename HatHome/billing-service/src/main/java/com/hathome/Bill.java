package com.hathome;

import java.util.Date;

/**
 * Created by Acer on 6/4/2560.
 */
public class Bill {
    private long id;
    private long user_id;
    private String address;
    private String date;
    private long cart_id;
    private double cart_cost;
    private double shipping_cost;
    private double total;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCart_id() {
        return cart_id;
    }

    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCart_cost() {
        return cart_cost;
    }

    public void setCart_cost(double cart_cost) {
        this.cart_cost = cart_cost;
    }

    public double getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(double shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
