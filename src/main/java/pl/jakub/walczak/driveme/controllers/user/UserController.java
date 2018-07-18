package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jakub.walczak.driveme.services.user.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
}
