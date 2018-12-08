package pl.jakub.walczak.driveme.controllers.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.login.UserCredentialsDTO;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity login(@RequestBody UserCredentialsDTO user) {
        return ResponseEntity.ok().build();
    }
}
