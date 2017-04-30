package hathome.userservice.user;

/**
 * Created by amliomnie on 30-Apr-17.
 */
public class UserSignupException  extends RuntimeException  {
    public UserSignupException(Exception e , String email) {
        super("Could not insert user " + email + e);
    }
}
