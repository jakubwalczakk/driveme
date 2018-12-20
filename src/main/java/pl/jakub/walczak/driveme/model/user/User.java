package pl.jakub.walczak.driveme.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.enums.UserRole;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@ToString(exclude = "password")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @Enumerated
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;
    @Column(name = "activity")
    private Boolean active;
}
