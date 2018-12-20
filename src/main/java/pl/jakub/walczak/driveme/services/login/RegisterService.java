package pl.jakub.walczak.driveme.services.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.RegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.user.UserRepository;
import pl.jakub.walczak.driveme.services.mail.MailerService;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.Validator;

@Service
@Slf4j
public class RegisterService {

    private UserService userService;
    private UserRepository userRepository;
    private RegistrationMapper registrationMapper;
    private MailerService mailerService;

    @Autowired
    public RegisterService(UserService userService, UserRepository userRepository, RegistrationMapper registrationMapper,
                           MailerService mailerService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.registrationMapper = registrationMapper;
        this.mailerService = mailerService;
    }

    public boolean createUser(RegistrationDTO userRegistrationDTO) {
        if (userService.existsByEmail(userRegistrationDTO.getEmail())) {
            return false;
        }

        log.info("Creating new User...");
        if (Validator.userRegistrationValidation(userRegistrationDTO)) {
            log.info("Validation of user registration DTO passed.");
            User user = registrationMapper.mapRegistrationDTOToUser(userRegistrationDTO);
            userRepository.save(user);
            try {
                mailerService.sendEmail(user.getEmail(), user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            log.warn("User registration DTO were incorrect!");
            throw new IllegalArgumentException("Cannot create a new user with given data");
        }
    }
}