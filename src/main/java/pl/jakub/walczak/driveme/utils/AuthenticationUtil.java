package pl.jakub.walczak.driveme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

import java.util.Optional;

@Component
public class AuthenticationUtil {

    private UserRepository userRepository;

    @Autowired
    public AuthenticationUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentLoggedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> currentLoggedUser = userRepository.findUserByEmail(authentication.getName());

        if (currentLoggedUser.isPresent()) {
            return currentLoggedUser.get();
        }

        throw new SecurityException("Current profile was not found in database!");
    }

    public boolean isCurrentProfileId(Long id) {
        return id.equals(getCurrentLoggedUser().getId());
    }
}
