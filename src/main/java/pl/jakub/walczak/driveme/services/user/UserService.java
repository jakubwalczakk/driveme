package pl.jakub.walczak.driveme.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.dto.user.UserRegistrationDTO;
import pl.jakub.walczak.driveme.mappers.user.RegistrationMapper;
import pl.jakub.walczak.driveme.mappers.user.UserBasicMapper;
import pl.jakub.walczak.driveme.mappers.user.UserMapper;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RegistrationMapper registrationMapper;
    private UserBasicMapper userBasicMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, RegistrationMapper registrationMapper,
                       UserBasicMapper userBasicMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.registrationMapper = registrationMapper;
        this.userBasicMapper = userBasicMapper;
    }

    // -- methods for controller --
    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        log.info("Creating new User...");
//        if (Validator.userRegistrationValidation(userRegistrationDTO)) {
//            log.info("Validation of user registration DTO passed.");
//            User user = registrationMapper.mapRegistrationDTOToUser(userRegistrationDTO);
//            return userRepository.save(user);
//        } else {
//            log.warn("User registration DTO were incorrect!");
//            throw new IllegalArgumentException("Cannot create a new user with given data");
//        }
        return null;
    }

    public void deleteUser(Long id) {
        log.info("Deleting the User with id = " + id);
        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isPresent()) {
            User user = userToDelete.get();
            user.setActive(false);
            userRepository.save(user);
        } else {
            throw new NoSuchElementException("Cannot DELETE User with given id = " + id);
        }
    }

    public UserDTO getUser(Long id) {
        log.info("Getting the User with id = " + id);
        Optional<User> optionalUser = userRepository.findById(id);
        return mapModelToDTO(optionalUser.orElseThrow(() ->
                new NoSuchElementException("Cannot GET User with given id = " + id)), UserDTO.builder().build());
    }

    public List<UserDTO> getAll() {
        log.info("Getting all Users");
        return findAll().stream().map(user -> mapModelToDTO(user, UserDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    // -- mapper methods --
    public UserDTO mapModelToDTO(User model, UserDTO dto) {
        return userMapper.mapModelToDTO(model, dto);
    }

    public User mapDTOToModel(UserDTO dto, User model) {
        if (dto.getId() != null) {
            Optional<User> optionalUser = userRepository.findById(dto.getId());
            model = optionalUser.orElse(model);
        }
        model = userMapper.mapDTOToModel(dto, model);
        return userRepository.save(model);
    }

    public UserBasicDTO mapUserBasicModelToDTO(User model, UserBasicDTO dto) {
        return userBasicMapper.mapModelTODTO(model, dto);
    }

    public User mapUserBasicDTOToModel(UserBasicDTO dto) {
        if (dto.getId() != null) {
            Optional<User> optionalUser = userRepository.findById(dto.getId());
            return optionalUser.orElseThrow(() ->
                    new NoSuchElementException("Cannot MAP UserBasicDTO into User model"));
        }
        throw new NoSuchElementException("Cannot MAP UserBasicDTO into User model");
    }
}
