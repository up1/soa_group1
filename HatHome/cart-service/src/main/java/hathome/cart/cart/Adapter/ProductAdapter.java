package hathome.cart.cart.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mark on 4/19/2017.
 */

@Service
public class ProductAdapter {

    String ip = "128.199.126.179";

    public Product getProductDetail(long productId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://"+ip+":9004/products/" + productId;
        Product productItem = restTemplate.getForObject(url, Product.class);

        return  productItem;
    }
}
