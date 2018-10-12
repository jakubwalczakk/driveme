package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.UserRegistrationDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class RegistrationMapper {

    public User mapDTOToModel(UserRegistrationDTO dto) {
        User model = new User();
        model.setName(dto.getName());
        model.setSurname(dto.getSurname());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        try {
            model.setUserRole(UserRole.valueOf(dto.getUserRole()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        return model;
    }
}
