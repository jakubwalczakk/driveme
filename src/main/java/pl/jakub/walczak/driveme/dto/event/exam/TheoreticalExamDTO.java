package pl.jakub.walczak.driveme.dto.event.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TheoreticalExamDTO extends ExamDTO {

    private Integer scoredPoints;
    private Double result;
}