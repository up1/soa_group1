package hathome.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/")
    public String index(){
        return "Hello, I'm Cart-service \n -------------------------- \n author : Marktrs";
    }

    @RequestMapping("/cart")
    public List<Cart> getAllCartItems(){
        return this.cartRepository.getUnpaidItemInCart();
    }

    @GetMapping("/cart/{userId}")
    public  List<Cart> getProductInCartById(@PathVariable String userId){
        //todo getCart API need to get product data from get product API
        return this.cartRepository.getItemInCartByUserId(userId);
    }

    @RequestMapping(value = "/cart",method = RequestMethod.PUT)
    public void updateProductInCart(@PathVariable String orderId, Integer amount){
        //todo Updating should able to multiple update in one query
        this.cartRepository.updateAmount(orderId, amount);
    }
}
