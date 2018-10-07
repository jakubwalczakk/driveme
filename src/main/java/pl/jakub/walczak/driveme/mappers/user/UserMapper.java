package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class UserMapper {

    public UserDTO mapModelToDTO(User model, UserDTO dto) {
        return dto;
    }

    public User mapDTOToModel(UserDTO dto, User model) {
        return model;
    }
}
