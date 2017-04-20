package hathome.wishlist.service.wishlist;

/**
 * Created by Plern on 5/3/2560.
 */
import hathome.wishlist.service.wishlist.Adapter.ProductAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class WishlistController {

    private final WishlistRepository wishlistRepository;
    private ProductAdapter productAdapter;

    public WishlistController(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

//    public List<Wishlist> getAllWishlistItem(){
//        List<Wishlist> wishlists = this.wishlistRepository.
//        return getProductDetail(wishlists);
//    }

    @GetMapping("/wishlist")
    public Wishlist getWishlist(@RequestParam(value = "wishlist_id", defaultValue="1") int id){
        return this.wishlistRepository.findById((long) id);
    }

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


//    private List<Wishlist> getProductDetail(List<Wishlist> wishlists){
//        if (wishlists == null || wishlists.isEmpty()){
//            System.out.println("Wishlist is Empty");
//        }else {
//            System.out.println("" + wishlists.size());
//            for (Wishlist w : wishlists){
//                w
//            }
//        }
//        return wishlists;
//    }
}
