package pl.jakub.walczak.driveme.dto.event.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.event.EventDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.event.Event;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO extends EventDTO {

    private Boolean passed;
}
