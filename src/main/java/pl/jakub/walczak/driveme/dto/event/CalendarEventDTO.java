package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEventDTO {

    private Long id;
    private StudentDTO student;
    private InstructorDTO instructor;
    private CarDTO car;
    private Double eventDuration;
    private DrivingCityDTO drivingCity;
}