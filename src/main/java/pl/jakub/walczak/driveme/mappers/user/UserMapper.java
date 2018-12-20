package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO mapModelToDTO(User model, UserDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setUserRole(model.getUserRole().getValue());
        dto.setActive(model.getActive());
        return dto;
    }

    public User mapDTOToModel(UserDTO dto, User model) {
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setSurname(dto.getSurname());
        model.setEmail(dto.getEmail());
        model.setPassword(passwordEncoder.encode(dto.getPassword()));
        model.setPhoneNumber(dto.getPhoneNumber());
        try {
            model.setUserRole(UserRole.of(dto.getUserRole()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        model.setActive(dto.getActive());
        return model;
    }
}
