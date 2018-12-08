package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.user.User;

@Component
public class UserBasicMapper {

    public UserBasicDTO mapModelTODTO(User model, UserBasicDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setUserRole(model.getUserRole() != null ? model.getUserRole().toString() : null);
        return dto;
    }
}
