package hathome.cart.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public String index() {
        System.out.println( "Hello, Im cart service" );
        return " Hello, I'm Cart-service " +
        "\n -------------------------- " +
        "\n author : Marktrs";
    }

    @GetMapping("/cart")
    public List<Cart> getAllCartItems() {
        System.out.println( "listing all unpaid order" );
        return this.cartRepository.getUnpaidItemInCart();
    }

    /*-------------- Retrieve product in cart --------------*/

    @GetMapping("/cart/{userId}")
    public List<Cart> getProductInCartById(@PathVariable Long userId) {
        //todo getCart API need to get product data from Product-service
        return this.cartRepository.getItemInCartByUserId(userId);
    }

    /*-------------- Update product in cart --------------*/

    /*
    @RequestMapping(value = "/cart",method = RequestMethod.PUT)
    public void updateProductInCart(@RequestBody() Map<String, Object> bodyJSON)
            throws JsonProcessingException{
        //todo Updating should able to multiple update in one query
        this.cartRepository.updateAmount(orderId, amount);
    }
    */

    /*-------------- Add product to cart --------------*/

    @RequestMapping(
            value = "/cart",
            method = RequestMethod.POST)
    public ResponseEntity<Cart> addProductToCart(@RequestBody Cart cartItem)
            throws JsonProcessingException {

        //Todo: Get data from UI click ( eg. productId + userId )

        try {
            this.cartRepository.addProduct(cartItem);
            System.out.println("Success");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
