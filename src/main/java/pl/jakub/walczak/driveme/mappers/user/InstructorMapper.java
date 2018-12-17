package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.user.Instructor;

import java.util.Base64;

@Component
public class InstructorMapper {

    public InstructorDTO mapModelToDTO(Instructor model, InstructorDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setUserRole(model.getUserRole().toString());
        dto.setActive(model.getActive());
        dto.setAvailableHours(model.getAvailableHours());
        dto.setTakenHours(model.getTakenHours());
        dto.setWorkingHours(model.getWorkingHours());
        try {
            dto.setInstructorPhoto(Base64.getEncoder().encodeToString(model.getInstructorPhoto()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setInstructorPhoto(null);
        }
        return dto;
    }

    public Instructor mapDTOToModel(InstructorDTO dto, Instructor model) {

        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setSurname(dto.getSurname());
        model.setEmail(dto.getEmail());
        model.setPhoneNumber(dto.getPhoneNumber());

        try {
            model.setUserRole(UserRole.valueOf(dto.getUserRole().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }

        model.setActive(dto.getActive());
        model.setAvailableHours(dto.getAvailableHours());
        model.setTakenHours(dto.getTakenHours());
        model.setWorkingHours(dto.getWorkingHours());
        try {
            model.setInstructorPhoto(Base64.getDecoder().decode(dto.getInstructorPhoto()));
        } catch (Exception e) {
            e.printStackTrace();
            model.setInstructorPhoto(null);
        }
        return model;
    }
}
