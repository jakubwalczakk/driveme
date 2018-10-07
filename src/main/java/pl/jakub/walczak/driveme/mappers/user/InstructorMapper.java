package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.model.user.Instructor;

@Component
public class InstructorMapper {

    public InstructorDTO mapModelToDTO(Instructor model, InstructorDTO dto) {
        return dto;
    }

    public Instructor mapDTOToModel(InstructorDTO dto, Instructor model) {
        return model;
    }
}
