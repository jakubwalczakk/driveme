package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;

@Component
public class TheoreticalExamMapper {

    public TheoreticalExamDTO mapModelToDTO(TheoreticalExam model, TheoreticalExamDTO dto) {
        return dto;
    }

    public TheoreticalExam mapDTOToModel(TheoreticalExamDTO dto, TheoreticalExam model) {
        return model;
    }
}
