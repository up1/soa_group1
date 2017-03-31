package hathome.wishlist.service.wishlist;

/**
 * Created by Plern on 5/3/2560.
 */
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:9007")
public class WishlistController {

    private final WishlistRepository wishlistRepository;

    public WishlistController(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @GetMapping("/wishlist")
    public Wishlist getWishlist(@RequestParam(value = "wishlist_id", defaultValue="1") int id){
        return this.wishlistRepository.findById((long) id);
    }

//    private String hi = "Say hi";
//    @RequestMapping("/hello")
//    public String hello(){
//        return hi;
//    }
}
