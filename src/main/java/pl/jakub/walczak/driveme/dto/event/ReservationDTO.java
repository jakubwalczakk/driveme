package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO extends EventDTO {

    private String carBrand;
    private String drivingCity;
    private Boolean accepted;
}
