package com.hathome.adapter;

import com.hathome.Bill;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.*;

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

    public boolean checkOutFromCart(long userId){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String url = "http://localhost:9003/cart/{userId}";
        MultiValueMap<String, Long> params = new LinkedMultiValueMap<String, Long>();
        params.add("userId", userId);
        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Long>> requestEntity = new HttpEntity<MultiValueMap<String, Long>>(params, requestHeader);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class, userId);
        Object object = response.getBody();
        if(object == null){
            return false;
        }else{
            return true;
        }
    }
}

