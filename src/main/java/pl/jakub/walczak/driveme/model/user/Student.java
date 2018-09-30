package pl.jakub.walczak.driveme.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.reservation.Reservation;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "students")
public class Student extends User {

    @Column(name = "pesel", nullable = false)
    private String pesel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Address address;
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Payment> payments;
    @OneToOne(cascade = CascadeType.ALL)
    private Course course;
    @OneToMany(mappedBy = "student")
    private Set<Reservation> reservations;

}
