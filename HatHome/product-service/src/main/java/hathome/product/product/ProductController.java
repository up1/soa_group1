package hathome.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @RequestMapping("/products")
    public List<Product> getAllProduct(){
        return this.productRepository.findAllProduct();
    }

    @RequestMapping("/search")
    public List<Product> searchProduct(
        @RequestParam(value = "keyword", defaultValue = "") String keyword){
        return this.productRepository.searchProduct(keyword);
    }
}
