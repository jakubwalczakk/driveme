package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;

import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity loginUser(@RequestParam String email, @RequestParam String password) {

        Optional<User> optionalUser = userService.findUserByEmailAndPassword(email, password);
        if (optionalUser.isPresent()) {
            return new ResponseEntity(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }

    }
}
