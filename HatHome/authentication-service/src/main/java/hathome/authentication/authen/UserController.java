package hathome.authentication.authen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/login")
    public String getLoginPage(
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "password", defaultValue = "") String password){
        User user = this.userRepository.getLoginPage(email, password);
        String show = "";
        if (user.getEmail() != null){
           show = "Welcome! " + user.getEmail();
        }else {
            show = "Undefined your account";
        }

        return show;
    }


    @GetMapping("/mimi")
    public String getAuthen() {
        return "mimi";

    }

    @RequestMapping("/hello")
    public String sayHi(){
        return "Hi";
    }



}
