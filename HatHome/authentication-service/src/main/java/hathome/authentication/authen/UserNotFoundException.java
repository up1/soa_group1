package hathome.authentication.authen;

/**
 * Created by amliomnie on 30-Apr-17.
 */
public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(Exception e , String email) {
        super("Could not find user " + email + e);
    }
}
