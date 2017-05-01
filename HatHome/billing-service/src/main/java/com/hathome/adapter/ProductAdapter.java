package com.hathome.adapter;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Acer on 23/4/2560.
 */
public class ProductAdapter {

    public Product getProductById(long id){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://128.199.126.179:9004/products/" + id;
        return restTemplate.getForObject(url, Product.class);
    }
}
