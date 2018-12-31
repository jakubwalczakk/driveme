package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.AdminDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.user.Admin;

@Component
public class AdminMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AdminDTO mapModelToDTO(Admin model, AdminDTO dto) {
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

    public Admin mapDTOToModel(AdminDTO dto, Admin model) {
        model.setId(dto.getId() == null ? model.getId() : dto.getId());
        model.setName(dto.getName() == null ? model.getName() : dto.getName());
        model.setSurname(dto.getSurname() == null ? model.getSurname() : dto.getSurname());
        model.setEmail(dto.getEmail() == null ? model.getEmail() : dto.getEmail());
        model.setPassword(dto.getPassword() == null ? model.getPassword() : passwordEncoder.encode(dto.getPassword()));
        model.setPhoneNumber(dto.getPhoneNumber() == null ? model.getPhoneNumber() : dto.getPhoneNumber());
        try {
            model.setUserRole(dto.getUserRole() == null ? model.getUserRole() :
                    UserRole.of(dto.getUserRole()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        model.setActive(dto.getActive() == null ? model.getActive() : dto.getActive());
        return model;
    }
}
