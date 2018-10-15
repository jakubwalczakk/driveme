package pl.jakub.walczak.driveme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String userRole;
}
