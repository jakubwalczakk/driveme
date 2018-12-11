package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventDTO {

    private Long id;
    private Instant startDate;
    private Instant finishDate;
    private CarBasicDTO car;
    private String drivingCity;
    private UserBasicDTO student;
    private UserBasicDTO instructor;
}
