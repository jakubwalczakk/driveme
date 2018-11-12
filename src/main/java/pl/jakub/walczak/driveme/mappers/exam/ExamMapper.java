package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.exam.Exam;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;

@Component
public class ExamMapper {

    @Autowired
    private UserService userService;

    public ExamDTO mapModelToDTO(Exam model, ExamDTO dto) {
        dto.setId(model.getId());
        dto.setDateOfExam(model.getDateOfExam());
        dto.setActive(model.getActive());
        dto.setPassed(model.getPassed());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        return dto;
    }

    public Exam mapDTOToModel(ExamDTO dto, Exam model) {
        model.setId(dto.getId());
        model.setDateOfExam(dto.getDateOfExam());
        model.setActive(dto.getActive());
        model.setPassed(dto.getPassed());
        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student)
            model.setStudent((Student) student);
        return model;
    }
}
