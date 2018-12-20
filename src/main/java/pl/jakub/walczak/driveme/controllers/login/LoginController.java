package pl.jakub.walczak.driveme.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.login.UserCredentialsDTO;
import pl.jakub.walczak.driveme.dto.user.RegistrationDTO;
import pl.jakub.walczak.driveme.security.ApiResponse;
import pl.jakub.walczak.driveme.security.JwtAuthenticationDTO;
import pl.jakub.walczak.driveme.security.JwtTokenProvider;
import pl.jakub.walczak.driveme.services.login.RegisterService;
import pl.jakub.walczak.driveme.services.user.UserService;

import static pl.jakub.walczak.driveme.security.JwtConstants.BEARER;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private RegisterService registerService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                           UserService userService, RegisterService registerService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.registerService = registerService;
    }

    @PostMapping("/signin")
    public ResponseEntity login(@RequestBody UserCredentialsDTO loginRequest) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final JwtAuthenticationDTO jwtToken = JwtAuthenticationDTO.builder()
                    .accessToken(jwtTokenProvider.generateToken(authentication))
                    .tokenType(BEARER)
                    .build();
            return ResponseEntity.ok(jwtToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.builder().success(false).message(e.getMessage()).build());
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationDTO registrationDTO) {
        try {
            if (registerService.createUser(registrationDTO)) {
                return ResponseEntity.ok(ApiResponse.builder()
                        .success(true).message("Użytkownik zarejestrowany poprawnie.").build());
            }
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .success(false).message("Ten adres e-mail jest już używany!").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                    .success(false).message(e.getMessage()).build());
        }
    }
}
