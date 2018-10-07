package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.model.user.Student;

@Component
public class StudentMapper {

    public StudentDTO mapModelToDTO(Student model, StudentDTO dto) {
        return dto;
    }

    public Student mapDTOToModel(StudentDTO dto, Student model) {
        return model;
    }
}
