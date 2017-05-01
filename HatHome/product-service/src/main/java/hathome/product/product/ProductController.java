package hathome.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin("http://localhost:8080")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @RequestMapping("/recentproducts")
    public List<Product> getRecentProduct(){
        return this.productRepository.findRecentProduct();
    }

    @RequestMapping("/products")
    public List<Product> getAllProductInPage(){
        return this.productRepository.findAllProduct();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable long id) {
        return this.productRepository.findProductById((long) id);
    }

    @RequestMapping("/search")
    public SearchResult searchProduct(
        @RequestParam(value = "keyword", defaultValue = "") String keyword){
        SearchResult searchResult = new SearchResult();
        searchResult.setKeyword(keyword);
        searchResult.setResults(this.productRepository.searchProduct(keyword));
        return searchResult;
    }
}
