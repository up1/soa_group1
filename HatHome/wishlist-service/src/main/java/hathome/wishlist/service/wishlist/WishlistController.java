package hathome.wishlist.service.wishlist;

/**
 * Created by Plern on 5/3/2560.
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/wishlist")
public class WishlistController {
    @GetMapping()
    public void getWishlist(){

    }

    private String hi = "Say hi";
    @RequestMapping("/hello")
    public String hello(){
        return hi;
    }
}
