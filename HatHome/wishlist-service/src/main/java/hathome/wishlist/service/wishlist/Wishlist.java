package hathome.wishlist.service.wishlist;

import hathome.wishlist.service.wishlist.Adapter.Product;

/**
 * Created by Plern on 5/3/2560.
 */
public class Wishlist {
    private long wishlist_id;
    private long user_id;
    private long product_id;
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public long getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(long wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
