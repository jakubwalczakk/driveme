package pl.jakub.walczak.driveme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends UserDTO {

    private String pesel;
    private AddressDTO address;
    private Instant registrationDate;
    private Set<ReservationDTO> reservations;
    private Set<DrivingDTO> drivings;
}
