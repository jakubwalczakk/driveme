package pl.jakub.walczak.driveme.dto.login;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    private String email;
    private String password;
}
