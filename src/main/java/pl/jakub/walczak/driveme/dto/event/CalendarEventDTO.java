package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventDTO {

    private Long id;
    private Instant date;
    private Integer minutesOfEvent;
    private CarDTO car;
    private DrivingCityDTO drivingCity;
    private UserBasicDTO student;
    private UserBasicDTO instructor;
}
