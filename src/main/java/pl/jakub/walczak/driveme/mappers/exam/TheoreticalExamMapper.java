package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;

@Component
public class TheoreticalExamMapper {

    @Autowired
    private UserService userService;

    public TheoreticalExamDTO mapModelToDTO(TheoreticalExam model, TheoreticalExamDTO dto) {
        dto.setId(model.getId());
        dto.setDateOfExam(model.getDateOfExam());
        dto.setActive(model.getActive());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setScoredPoints(model.getScoredPoints());
        dto.setResult(model.getResult());
        dto.setPassed(model.getPassed());
        return dto;
    }

    public TheoreticalExam mapDTOToModel(TheoreticalExamDTO dto, TheoreticalExam model) {
        model.setId(dto.getId());
        model.setDateOfExam(dto.getDateOfExam());
        model.setActive(dto.getActive());
        model.setScoredPoints(model.getScoredPoints());
        model.setResult(dto.getResult());
        model.setPassed(dto.getPassed());
        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student)
            model.setStudent((Student) student);
        return model;
    }
}
