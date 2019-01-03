package pl.jakub.walczak.driveme.mappers.event.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.exam.ExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.event.exam.Exam;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

@Component
public class ExamMapper {

    @Autowired
    private UserService userService;

    public ExamDTO mapModelToDTO(Exam model, ExamDTO dto) {
        dto.setId(model.getId());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setDuration(model.getDuration());
        dto.setFinishDate(DateFormatter.formatDateToString(model.getFinishDate()));
        dto.setPassed(model.getPassed());
        return dto;
    }

    public Exam mapDTOToModel(ExamDTO dto, Exam model) {
        model.setId(dto.getId());
        model.setStartDate(dto.getStartDate() == null ? model.getStartDate() : DateFormatter.parseStringToInstant(dto.getStartDate()));
        model.setDuration(dto.getDuration() == null ? model.getDuration() : dto.getDuration());
        model.setFinishDate(dto.getFinishDate() == null ? model.getFinishDate() : DateFormatter.parseStringToInstant(dto.getFinishDate()));

        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }
        model.setPassed(dto.getPassed() == null ? model.getPassed() : dto.getPassed());
        return model;
    }
}
