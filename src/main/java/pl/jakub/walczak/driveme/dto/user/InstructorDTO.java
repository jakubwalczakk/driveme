package pl.jakub.walczak.driveme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO extends UserDTO {

    private Integer availableHours;
    private Integer takenHours;
    private Integer workingHours;
}
