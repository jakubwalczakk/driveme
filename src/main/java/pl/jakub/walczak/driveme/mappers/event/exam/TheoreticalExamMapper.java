package pl.jakub.walczak.driveme.mappers.event.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.event.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

@Component
public class TheoreticalExamMapper {

    @Autowired
    private UserService userService;

    public TheoreticalExamDTO mapModelToDTO(TheoreticalExam model, TheoreticalExamDTO dto) {

        dto.setId(model.getId());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setScoredPoints(model.getScoredPoints());
        dto.setResult(model.getResult());
        dto.setPassed(model.getPassed());
        return dto;
    }

    public TheoreticalExam mapDTOToModel(TheoreticalExamDTO dto, TheoreticalExam model) {

        model.setId(dto.getId());
        model.setStartDate(DateFormatter.parseStringToInstant(dto.getStartDate()));

        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }
        model.setScoredPoints(dto.getScoredPoints());
        model.setResult(dto.getResult());
        model.setPassed(dto.getPassed());
        return model;
    }
}
