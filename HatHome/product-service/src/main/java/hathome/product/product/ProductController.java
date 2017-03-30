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

    @RequestMapping("/home")
    public List<Product> getRecentProduct(){
        return this.productRepository.findRecentProduct();
    }

    @RequestMapping("/search")
    public SearchResult searchProduct(
        @RequestParam(value = "keyword", defaultValue = "") String keyword,
        @RequestParam(value = "price", defaultValue = "0-10000") String price,
        @RequestParam(value = "color", defaultValue = "") String color,
        @RequestParam(value = "brand", defaultValue = "") String brand,
        @RequestParam(value = "type", defaultValue = "") String type){
        SearchResult searchResult = new SearchResult();
        searchResult.setKeyword(keyword);
        searchResult.setPrice(price);
        searchResult.setColor(color);
        searchResult.setBrand(brand);
        searchResult.setType(type);
        searchResult.setResults(this.productRepository.searchProduct(keyword, price, color, brand, type));
        return searchResult;
    }
}
