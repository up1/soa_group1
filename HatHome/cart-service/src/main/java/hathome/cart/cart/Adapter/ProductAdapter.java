package hathome.cart.cart.adapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Mark on 4/19/2017.
 */

@Service
public class ProductAdapter {

    String ip = System.getProperty("128.199.126.179");
    Socket socket = new Socket(ip, 9004);

    public ProductAdapter() throws IOException {
        //do nothing
    }


    public Product getProductDetail(long productId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://"+ip+":9004/products/" + productId;
        return  restTemplate.getForObject(url, Product.class);
    }
}
