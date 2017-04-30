package hathome.userservice.user;

/**
 * Created by amliomnie on 30-Apr-17.
 */
public class UserUpdateException extends RuntimeException {
    public UserUpdateException(Long id, Exception e) {
        super("Could not update user " + id + "cause :" + e.toString() );
    }
}
