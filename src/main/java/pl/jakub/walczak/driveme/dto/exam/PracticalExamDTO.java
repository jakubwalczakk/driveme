package pl.jakub.walczak.driveme.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExamDTO extends ExamDTO {

    private CarDTO car;
    private InstructorDTO instructor;
}
