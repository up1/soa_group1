package hathome.authentication.authen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users")
    public List<User> getAllUser(){
        return this.userRepository.findAllUser();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<User> checkLogin(
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "password", defaultValue = "") String password){
        User user = this.userRepository.checkLogin(email, password);
        HttpHeaders responseHeaders = new HttpHeaders();
        if (user.getEmail() != null){
            return new ResponseEntity<>(user, responseHeaders, HttpStatus.ACCEPTED);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
