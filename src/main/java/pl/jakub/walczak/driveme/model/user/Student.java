package pl.jakub.walczak.driveme.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "students")
public class Student extends User {

    @Column(name = "pesel", nullable = false)
    private String pesel;
    @ManyToOne//(cascade = CascadeType.ALL)
    private Address address;
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Course course;
    @OneToMany(mappedBy = "student")//, orphanRemoval = true)
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "student")//, orphanRemoval = true)
    private Set<Driving> drivings;
    //    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    //    private Set<Payment> payments;

}
