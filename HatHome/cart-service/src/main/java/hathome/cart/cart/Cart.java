package hathome.cart.cart;


/**
 * Created by Mark on 3/6/2017.
 */
public class Cart {
    private String id;
    private String user_id;
    private String product_id;
    private String amount;
    private String status;

    public void setId(String id) {
        this.id = id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
