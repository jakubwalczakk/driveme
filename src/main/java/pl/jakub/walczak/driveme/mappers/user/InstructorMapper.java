package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.model.user.Instructor;

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
        return dto;
    }

    //TODO
    public Instructor mapDTOToModel(InstructorDTO dto, Instructor model) {
        return model;
    }
}
