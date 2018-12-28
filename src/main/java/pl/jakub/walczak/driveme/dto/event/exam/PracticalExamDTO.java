package pl.jakub.walczak.driveme.dto.event.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExamDTO extends ExamDTO {

    private CarBasicDTO car;
    private UserBasicDTO instructor;
}
