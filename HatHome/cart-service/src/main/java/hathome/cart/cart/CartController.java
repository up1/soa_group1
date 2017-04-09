package hathome.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mark on 3/6/2017.
 */
@RestController
public class CartController {

    private CartRepository cartRepository;

    @Autowired
    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping("/cart")
    public List<Cart> getAllCartItems(){
        return this.cartRepository.getItemInCart();
    }

    @GetMapping("/cart/{userId}")
    public  List<Cart> getProductInCartById(@PathVariable String userId){
        //todo getCart API need to get product data from get product API
        return this.cartRepository.getItemInCartById(userId);
    }

    @RequestMapping("/cart/{productId}{amount}")
    public  void updateProductInCart(@PathVariable String productId, BigDecimal amount){
        //todo Updating should able to multiple update in one query
        this.cartRepository.updateAmount(productId, amount);
    }
}
