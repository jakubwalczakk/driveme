package pl.jakub.walczak.driveme.dto.event.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.event.EventDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO extends EventDTO {

    private Boolean passed;
}
