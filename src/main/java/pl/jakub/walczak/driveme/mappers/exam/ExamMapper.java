package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.model.exam.Exam;

@Component
public class ExamMapper {

    public ExamDTO mapModelToDTO(Exam model, ExamDTO dto) {
        dto.setId(model.getId());
        dto.setDateOfExam(dto.getDateOfExam());
        return dto;
    }

    public Exam mapDTOToModel(ExamDTO dto, Exam model) {
        model.setId(dto.getId());
        model.setDateOfExam(dto.getDateOfExam());
        return model;
    }
}
