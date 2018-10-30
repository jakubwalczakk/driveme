package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.UserRegistrationDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class RegistrationMapper {

    public User mapDTOToModel(UserRegistrationDTO dto) {
        User model = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        try {
            model.setUserRole(UserRole.valueOf(dto.getUserRole()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        return model;
    }
}
