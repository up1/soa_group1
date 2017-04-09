package hathome.userservice.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        String password = (String) bodyJSON.remove("password");

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(bodyJSON, User.class);

        try {
            this.userRepository.signup(user, password);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/{userId}")
    public User getUserDetail(@PathVariable Integer userId){
        return this.userRepository.findById((Integer) userId);
    }



}
