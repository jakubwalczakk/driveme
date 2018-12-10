package pl.jakub.walczak.driveme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    private String name;
    private String surname;
    private String email;
    @Size(min = 8)
    private String password;
    private String phoneNumber;
}
