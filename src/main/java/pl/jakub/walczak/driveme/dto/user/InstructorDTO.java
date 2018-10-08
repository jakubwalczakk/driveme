package pl.jakub.walczak.driveme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;

import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO extends UserDTO {

    private Integer availableHours;
    private Integer takenHours;
    private Set<PracticalExamDTO> practicalExams;
    private Set<ReservationDTO> reservations;
    private Set<DrivingDTO> drivings;
}
