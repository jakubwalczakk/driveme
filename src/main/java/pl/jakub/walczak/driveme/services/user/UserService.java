package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
