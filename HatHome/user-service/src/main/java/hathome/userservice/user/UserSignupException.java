package hathome.userservice.user;

/**
 * Created by JesseGade on 5/2/2017.
 */
public class UserSignupException  extends RuntimeException  {
    public UserSignupException(Exception e , String email) {
        super("Could not insert user " + email + e);
    }
}
