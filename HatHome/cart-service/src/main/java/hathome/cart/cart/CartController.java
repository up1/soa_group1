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
@CrossOrigin
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

    /*-------------- Method for checking only --------------*/
    @GetMapping("/cart-check")
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
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.PUT)
    public ResponseEntity<Cart> updateProductInCart(@RequestBody Cart cartItem)
            throws JsonProcessingException{
        //todo Updating should able to multiple update in one query
        try {
            this.cartRepository.updateAmount(cartItem);;
            System.out.println("Updated");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------------- Add product to cart --------------*/
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.POST)
    public ResponseEntity<Cart> addProductToCart(@RequestBody Cart cartItem)
            throws JsonProcessingException {

        //Todo: Get data from UI click ( eg. productId + userId ).
        //Todo: Add multiple product by single query when click CART icon.
        try {
            this.cartRepository.addProduct(cartItem);
            System.out.println("Added");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------------- Remove product in cart --------------*/
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.DELETE)
    public ResponseEntity<Cart> removeProductInCart(@RequestBody Cart cartItem)
            throws JsonProcessingException {

        //Todo: Get data from UI click ( eg. productId + userId )
        //Todo: Check existing data before delete.
        //Todo: Delete multiple product by single query

        try {
            this.cartRepository.removeProduct(cartItem);
            System.out.println("Removed");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
