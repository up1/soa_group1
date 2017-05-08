package hathome.cart.cart.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mark on 4/19/2017.
 */

@Service
public class ProductAdapter {

    public Product getProductDetail(long productId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9004/products/" + productId;
        return  restTemplate.getForObject(url, Product.class);
    }
}
