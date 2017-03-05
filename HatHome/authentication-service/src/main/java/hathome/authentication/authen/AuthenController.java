package hathome.authentication.authen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class AuthenController {

    @GetMapping()
    public void getAuthen() {

    }
}
