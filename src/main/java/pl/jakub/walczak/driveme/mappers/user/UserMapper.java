package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class UserMapper {

    public UserDTO mapModelToDTO(User model, UserDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPasword(model.getPassword());
        dto.setUserRole(model.getUserRole().toString());
        dto.setActive(model.getActive());
        return dto;
    }

    public User mapDTOToModel(UserDTO dto, User model) {
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setSurname(dto.getSurname());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPasword());
        try{
            model.setUserRole(UserRole.valueOf(dto.getUserRole()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        model.setActive(dto.getActive());
        return model;
    }
}
