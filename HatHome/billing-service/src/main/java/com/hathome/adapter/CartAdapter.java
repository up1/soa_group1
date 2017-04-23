package com.hathome.adapter;

import com.hathome.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Acer on 23/4/2560.
 */
public class CartAdapter {
    public Cart getCartById(long userId){
        Cart cart = new Cart();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9003/cart/" + userId;
        ResponseEntity<CartItem[]> responseEntity = restTemplate.getForEntity(url, CartItem[].class);
        CartItem[] cartObj = responseEntity.getBody();
        List<CartItem> cartItems = new ArrayList<>(Arrays.asList(cartObj));

        List<Product> products = new ArrayList<>();
        double totalPrice = 0;
        for (CartItem cartitem: cartItems) {
            Product product = cartitem.getProduct();
            product.setAmount(cartitem.getAmount());
            products.add(product);
            totalPrice += cartitem.getProduct().getPrice() * cartitem.getAmount();
        }
        cart.setProducts(products);
        cart.setPrice(totalPrice);
        return  cart;
    }
}
