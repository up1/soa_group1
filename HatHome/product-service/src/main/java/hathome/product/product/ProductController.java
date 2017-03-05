package hathome.product.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class ProductController {

    @GetMapping()
    public void getAllProduct() {

    }
}
