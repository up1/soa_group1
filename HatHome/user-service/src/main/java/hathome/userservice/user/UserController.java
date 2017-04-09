package hathome.userservice.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by User on 9/4/2560.
 */

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
        public ResponseEntity signup(@RequestBody() Map<String, Object> bodyJSON ) throws JsonProcessingException {

        User user = new User();
        user.setEmail((String) bodyJSON.get("email"));
        user.setPassword((String) bodyJSON.get("password"));
        user.setAddress((String) bodyJSON.get("address"));

        if(this.userRepository.signup(user)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/user/{userId}")
    public User getUserDetail(@PathVariable Integer userId){
        return this.userRepository.findById((Integer) userId);
    }



}
