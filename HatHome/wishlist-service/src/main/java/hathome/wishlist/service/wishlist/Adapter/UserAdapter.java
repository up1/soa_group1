package hathome.wishlist.service.wishlist.Adapter;

import org.springframework.web.client.RestTemplate;

/**
 * Created by Plern on 24/4/2560.
 */
public class UserAdapter {
    public User getUserById(long userId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9001/user/" + userId;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
