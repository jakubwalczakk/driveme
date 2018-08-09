package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.PasswordEncryptor;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity loginUser(@RequestBody String email, @RequestBody String password) {

        try {
            password = PasswordEncryptor.getHashedPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Optional<User> optionalUser = userService.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getPassword().equals(password)) {
                return new ResponseEntity(optionalUser.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
