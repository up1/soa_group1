package hathome.wishlist.service.wishlist.Adapter;

/**
 * Created by Plern on 24/4/2560.
 */
public class User {
    private String email;
    private String password;
    private String address;
    private Long id;

    public User() {
    }

    public User(Long id, String email, String address) {
        this.email = email;
        this.address = address;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
