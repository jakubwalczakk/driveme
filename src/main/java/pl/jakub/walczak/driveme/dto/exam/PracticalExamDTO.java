package pl.jakub.walczak.driveme.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExamDTO extends ExamDTO {

    private CarDTO car;
    private InstructorDTO instructor;
}
