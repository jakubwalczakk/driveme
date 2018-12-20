package pl.jakub.walczak.driveme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String pesel;
    private String userRole;
    private AddressDTO address;
    private Integer workingHours;
}
