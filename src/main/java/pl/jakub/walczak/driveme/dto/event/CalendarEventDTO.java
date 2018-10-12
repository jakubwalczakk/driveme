package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventDTO {

    private Long id;
    private UserBasicDTO student;
    private UserBasicDTO instructor;
    private CarDTO car;
    private Double eventDuration;
    private DrivingCityDTO drivingCity;
}
