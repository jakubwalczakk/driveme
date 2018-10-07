package pl.jakub.walczak.driveme.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.mappers.user.UserMapper;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

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
}
