package hathome.userservice.user;

/**
 * Created by User on 9/4/2560.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not find user " + id);
    }
}
