package com.hathome.adapter;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Acer on 17/4/2560.
 */
public class UserAdapter {

    public User getUserById(long userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9007/user/" + userId;
        return restTemplate.getForObject(url, User.class);
    }
}
