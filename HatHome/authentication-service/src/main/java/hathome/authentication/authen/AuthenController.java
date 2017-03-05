package hathome.authentication.authen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class AuthenController {

    @GetMapping("/mimi")
    public String getAuthen() {
        return "mimi";

    }

    @RequestMapping("/hello")
    public String sayHi(){
        return "Hi";
    }
}
