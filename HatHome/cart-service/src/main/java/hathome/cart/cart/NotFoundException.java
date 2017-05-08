package hathome.cart.cart;

/**
 * Created by Mark on 5/8/2017.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(Exception e , String id) {
        super("Could not find this item : " + id + e);
    }
}
