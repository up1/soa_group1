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
        this.productAdapter = new ProductAdapter();
    }

    @GetMapping("/wishlist/user/{user_id}")
    public List<Wishlist> getWishlist(@PathVariable int user_id) {
        List<Wishlist> wishlists = this.wishlistRepository.findAllWishlist((long) user_id);
        return getProductDetail(wishlists);
//        return this.wishlistRepository.findById((long) user_id);
    }

    @RequestMapping(value = "/wishlist", method = RequestMethod.POST)
    public String insert(@RequestBody Wishlist wishlist) {
        wishlistRepository.addWishlist(wishlist);
        return "can post++++++++++" + wishlist.getWishlist_id();
    }

    @RequestMapping(value = "/wishlist/{pdId}/user/{usId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("pdId") long productId, @PathVariable("usId") long userId) {
        wishlistRepository.deleteWishlist(productId, userId);
        return "can delete----------" + productId + "of" + userId;
    }

    @GetMapping
    private List<Wishlist> getProductDetail(List<Wishlist> wishlists) {
        if (wishlists == null || wishlists.isEmpty()) {
            System.out.println("Wishlist is Empty");
        } else {
            System.out.println("size" + wishlists.size());
            for (Wishlist w : wishlists) {
                w.setProduct(this.productAdapter.getProductDetail(w.getProduct_id()));
            }
        }
        return wishlists;
    }
}
