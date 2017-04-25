package com.hathome.adapter;

import java.util.List;

/**
 * Created by Acer on 23/4/2560.
 */
public class Cart {
    private double price;
    private List<Product> products;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
