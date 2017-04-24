package hathome.wishlist.service.wishlist;

/**
 * Created by Plern on 5/3/2560.
 */
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

        @RequestMapping(value = "/wishlist", method = RequestMethod.POST)
        public String insert(@RequestBody Wishlist wishlist){
            wishlistRepository.addWishlist(wishlist);
            return "can post++++++++++"+wishlist.getWishlist_id();
        }

        @RequestMapping(value = "/wishlist", method = RequestMethod.DELETE)
        public String delete(@RequestBody Wishlist wishlist){
            wishlistRepository.deleteWishlist(wishlist);
            return "can delete----------"+wishlist.getWishlist_id();
        }

//    @GetMapping(path = "")
//    public @ResponseBody String insert (@RequestParam Wishlist wishlist, @RequestParam)
}
