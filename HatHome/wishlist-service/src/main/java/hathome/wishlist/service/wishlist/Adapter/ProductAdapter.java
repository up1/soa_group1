package hathome.wishlist.service.wishlist.adapter;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductAdapter {
    public Product getProductDetail(long productId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://128.199.126.179:9004/products/" + productId;
        return restTemplate.getForObject(url, Product.class);
    }
}
