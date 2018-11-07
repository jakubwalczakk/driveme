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
public class StudentRegistrationDTO extends UserRegistrationDTO {

    private String pesel;
    private AddressDTO address;
}
