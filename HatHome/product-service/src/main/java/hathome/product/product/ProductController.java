package hathome.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin
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

    @GetMapping("/products/pages/{page}")
    public ProductPage getProductInPage(@PathVariable int page) {
        ProductPage productPage = new ProductPage();
        List<Product> products = this.productRepository.findAllProduct();
        productPage.setCurrentPage(page);
        productPage.setLastPage((int)Math.ceil(products.size()/9.0));
        productPage.setNextPageUrl("http://128.199.126.179:9004/products/pages/"+Integer.toString(page+1));
        productPage.setPrevPageUrl("http://128.199.126.179:9004/products/pages/"+Integer.toString(page-1));
        productPage.setProducts(this.productRepository.findProductsInPage(page));
        return productPage;
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
