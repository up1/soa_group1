package com.hathome.adapter;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Acer on 23/4/2560.
 */
public class ProductAdapter {

    public Product getProductById(long id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9004/products/" + id;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
