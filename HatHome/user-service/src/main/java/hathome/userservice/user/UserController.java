package hathome.userservice.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

        if(this.userRepository.signup((String) bodyJSON.get("email"), (String) bodyJSON.get("password"), (String) bodyJSON.get("address"))){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
