package hathome.wishlist.service.wishlist.adapter;

import org.springframework.web.client.RestTemplate;

public class UserAdapter {
    public User getUserById(long userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://128.199.64.78:9007/user/" + userId;
        return restTemplate.getForObject(url, User.class);
    }
}
