package pl.jakub.walczak.driveme.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheoreticalExamDTO extends ExamDTO{

    private Integer scoredPoints;
}
