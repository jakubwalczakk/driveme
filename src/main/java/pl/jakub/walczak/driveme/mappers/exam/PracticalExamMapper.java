package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;

@Component
public class PracticalExamMapper {

    public PracticalExamDTO mapModelToDTO(PracticalExam model, PracticalExamDTO dto) {
        return dto;
    }

    public PracticalExam mapDTOToModel(PracticalExamDTO dto, PracticalExam model) {
        return model;
    }
}
