package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DrivingDTO extends EventDTO {

    private UserBasicDTO instructor;
    private CarBasicDTO car;
    private String drivingCity;
    private String title;
    private String rating;
    private String comment;
}
