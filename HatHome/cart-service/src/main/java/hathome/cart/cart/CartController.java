package hathome.cart.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import hathome.cart.cart.Adapter.Product;
import hathome.cart.cart.Adapter.ProductAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mark on 3/6/2017.
 */
@RestController
@CrossOrigin
public class CartController {

    private CartRepository cartRepository;
    private ProductAdapter productAdapter;

    @Autowired
    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        productAdapter = new ProductAdapter();
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
        List<Cart> carts = this.cartRepository.getUnpaidItemInCart();
        return attachProductDetailToCartList(carts);
    }


    /*-------------- Retrieve product in cart --------------*/
    @GetMapping("/cart/{userId}")
    public List<Cart> getProductInCartById(@PathVariable Long userId) {
        //todo getCart API need to get product data from Product-service
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
            if (cartItem.size() > 0){
                this.cartRepository.updateMultiple(cartItem);;
                System.out.println("Updated");
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
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

        //Todo: Add multiple product by single query when click CART icon.

        try {
            if(cartItem.getProduct_id() != 0){
                this.cartRepository.addProduct(cartItem);
                System.out.println("Added");
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                System.out.println("Product id is missing");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*-------------- Remove product in cart --------------*/
    @RequestMapping(
            value = "/cart/{itemId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Cart> removeProductInCart(@PathVariable int itemId)
            throws JsonProcessingException {

        //Todo: Get data from UI click ( eg. productId + userId )
        //Todo: Check existing data before delete.
        //Todo: Delete multiple product by single query
        Cart cartItem = new Cart();
        cartItem.setId(itemId);
        System.out.println("Removing item " + cartItem.getId());

        try {
            this.cartRepository.removeProduct(cartItem);
            System.out.println("Removed");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
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
            System.out.println("Checked out");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Cart> attachProductDetailToCartList(List<Cart> carts){
        if(carts == null || carts.isEmpty()) {
            System.err.println("Cart list is Empty");
        }else {
            System.out.println("Carts size : " + carts.size());
            for (Cart c:carts){
                c.setProduct(this.productAdapter.getProductDetail(c.getProduct_id()));
            }
        }
        return carts;
    }

}
