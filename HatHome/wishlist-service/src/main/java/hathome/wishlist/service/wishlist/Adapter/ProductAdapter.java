package hathome.wishlist.service.wishlist.Adapter;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
/**
 * Created by Plern on 20/4/2560.
 */
@Service
public class ProductAdapter {
    public Product getProductDetail(long productId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9004/products/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);

        return product;
    }
}
