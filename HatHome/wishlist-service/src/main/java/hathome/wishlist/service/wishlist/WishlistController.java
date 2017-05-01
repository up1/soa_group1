package hathome.wishlist.service.wishlist;

/**
 * Created by Plern on 5/3/2560.
 */

import hathome.wishlist.service.wishlist.adapter.ProductAdapter;
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

    @GetMapping("/wishlist/user/{userId}")
    public List<Wishlist> getWishlist(@PathVariable int userId) {
        List<Wishlist> wishlists = this.wishlistRepository.findAllWishlist((long) userId);
        return getProductDetail(wishlists);
    }

    @RequestMapping(value = "/wishlist", method = RequestMethod.POST)
    public String insert(@RequestBody Wishlist wishlist) {
        wishlistRepository.addWishlist(wishlist);
        return "can post++++++++++" + wishlist.getWishlistId();
    }

    @RequestMapping(value = "/wishlist/{pdId}/user/{usId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("pdId") long productId, @PathVariable("usId") long userId) {
        wishlistRepository.deleteWishlist(productId, userId);
        return "can delete----------" + productId + "of" + userId;
    }

    @GetMapping
    private List<Wishlist> getProductDetail(List<Wishlist> wishlists) {
        if (wishlists == null || wishlists.isEmpty()) {
        }
        else{
            for (Wishlist w : wishlists) {
                w.setProduct(this.productAdapter.getProductDetail(w.getProductId()));
            }
        }
        return wishlists;
    }
}
