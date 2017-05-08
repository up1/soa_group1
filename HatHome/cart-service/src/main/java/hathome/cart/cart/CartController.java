package hathome.cart.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import hathome.cart.cart.adapter.ProductAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ProductAdapter productAdapter;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private static final String ERROR = "INTERNAL_SERVER_ERROR";

    @Autowired
    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        productAdapter = new ProductAdapter();
    }

    @RequestMapping("/")
    public String index() {
        return " Hello, I'm Cart-service " +
        "\n -------------------------- " +
        "\n author : Marktrs";
    }

    /*-------------- Method for checking only --------------*/
    @GetMapping("/cart-check")
    public List<Cart> getAllCartItems() {
        List<Cart> carts = this.cartRepository.getUnpaidItemInCart();
        return attachProductDetailToCartList(carts);
    }


    /*-------------- Retrieve product in cart --------------*/
    @GetMapping("/cart/{userId}")
    public List<Cart> getProductInCartById(@PathVariable Long userId) {
        List<Cart> carts = this.cartRepository.getItemInCartByUserId(userId);
        return attachProductDetailToCartList(carts);
    }


    /*-------------- Update product in cart --------------*/

    /**
     * Update product in cart
     * @param cartItem
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.PUT)
    public ResponseEntity<Cart> updateProductInCart(@RequestBody List<Cart> cartItem)
            throws JsonProcessingException{
        try {
            if (!cartItem.isEmpty()){
                this.cartRepository.updateMultiple(cartItem);
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (NotFoundException e) {
            logger.error("ERROR" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------------- Add product to cart --------------*/
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.POST)
    public ResponseEntity<Cart> addProductToCart(@RequestBody Cart cartItem)
            throws JsonProcessingException {
        try {
            if(cartItem.getProduct_id() != 0){
                this.cartRepository.addProduct(cartItem);
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (NotFoundException e) {
            logger.error("ERROR" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------------- Remove product in cart --------------*/
    @RequestMapping(
            value = "/cart/{itemId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Cart> removeProductInCart(@PathVariable int itemId)
            throws JsonProcessingException {
        Cart cartItem = new Cart();
        cartItem.setId(itemId);
        try {
            this.cartRepository.removeProduct(cartItem);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error("ERROR" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------------- Remove product after checked-out --------------*/
    @RequestMapping(
            value = "/cart/{userId}",
            method = RequestMethod.PUT)
    public ResponseEntity<Cart> checkoutProduct(@PathVariable Long userId)
            throws JsonProcessingException {
        try {
            this.cartRepository.checkOut(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            logger.error("ERROR" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Cart> attachProductDetailToCartList(List<Cart> carts){
        if(carts.isEmpty()) {
            logger.error("Cart list is Empty");
        }else {
            for (Cart c:carts){
                c.setProduct(this.productAdapter.getProductDetail(c.getProduct_id()));
            }
        }
        return carts;
    }

}
