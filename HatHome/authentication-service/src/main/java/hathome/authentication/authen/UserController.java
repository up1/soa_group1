package hathome.authentication.authen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> checkLogin(
            @RequestBody() Map<String, Object> bodyJSON ) throws JsonProcessingException {
        User user = this.userRepository.getUserByEmailAndPassword((String) bodyJSON.get("email"),(String) bodyJSON.get("password"));
        if (user != null){

        // Set claims (payloads)
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("address", user.getAddress());

        // Build JWT token
        String JWT = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + JWTAuthenticationService.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWTAuthenticationService.SECRET)
                .compact();

        HashMap<String, Object> mappingResponse = new HashMap<String, Object>() {{
            put("access_token", JWT);
            put("user", user);
        }};

        return new ResponseEntity<>(mappingResponse, HttpStatus.OK) ;

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
