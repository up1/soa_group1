package hathome.userservice.user;

/**
 * Created by User on 9/4/2560.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id, Exception e) {
        super("Could not find user " + id + " cause : " + e);
    }
}
