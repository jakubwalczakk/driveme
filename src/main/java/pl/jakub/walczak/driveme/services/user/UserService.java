package pl.jakub.walczak.driveme.services.user;

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
        User user = registrationMapper.mapDTOToModel(userRegistrationDTO);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isPresent()) {
            User user = userToDelete.get();
            user.setActive(false);
            userRepository.save(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<UserDTO> getAll() {
        return findAll().stream().map(user -> mapModelToDTO(user, UserDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
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
            if (optionalUser.isPresent()) {
                model = optionalUser.get();
            }
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
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            }
        }
        throw new NoSuchElementException();
    }
}
