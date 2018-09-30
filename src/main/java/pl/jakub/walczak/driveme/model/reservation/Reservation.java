package pl.jakub.walczak.driveme.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Instructor instructor;
    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;
    @Column(name = "reservation_date", nullable = false)
    private Date date;
    @Column(name = "driving_time", nullable = false)
    private Double drivingTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private DrivingCity drivingCity;
}
