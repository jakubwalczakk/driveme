package pl.jakub.walczak.driveme.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.course.Course;

import javax.persistence.*;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(exclude = "course")
@Entity(name = "students")
public class Student extends User {

    @Column(name = "pesel", nullable = false)
    private String pesel;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Course course;
}
