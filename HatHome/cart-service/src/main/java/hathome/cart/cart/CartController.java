package hathome.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Mark on 3/6/2017.
 */
public class CartController {

    private CartRepository cartRepository;

    @Autowired
    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping("/cart")
    public List<Cart> getCartItems(){
        return null;
    }

    @GetMapping("/test")
    public String getAuthen() {
        return "test";

    }
}
