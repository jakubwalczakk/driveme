package pl.jakub.walczak.driveme.controllers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.dto.user.UserRegistrationDTO;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.security.CustomUserDetails;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/user")
@Slf4j
public class UserController {

    private UserService userService;
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public UserController(UserService userService, AuthenticationUtil authenticationUtil) {
        this.userService = userService;
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            return ResponseEntity.ok(userService.createUser(userRegistrationDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/me")
    public CustomUserDetails getCurrentUser() {
        log.info("Getting current logged user");
        User currentUser = authenticationUtil.getCurrentLoggedUser();
        CustomUserDetails userDetails = CustomUserDetails.builder()
                .id(currentUser.getId())
                .name(currentUser.getName())
                .surname(currentUser.getSurname())
                .email(currentUser.getEmail())
                .password(currentUser.getPassword())
                .role(currentUser.getUserRole().getValue())
                .active(currentUser.getActive())
                .build();
        return userDetails;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
