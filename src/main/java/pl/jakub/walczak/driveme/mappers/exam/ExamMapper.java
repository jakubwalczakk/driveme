package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.model.exam.Exam;

@Component
public class ExamMapper {

    public ExamDTO mapModelToDTO(Exam model, ExamDTO dto) {
        return dto;
    }

    public Exam mapDTOToModel(ExamDTO dto, Exam model) {
        return model;
    }
}
