package pl.jakub.walczak.driveme.model.driving;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "drivings")
public class Driving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Instructor instructor;
    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;
    @Column(name = "driving_date", nullable = false)
    private Instant date;
    @Column(name = "driving_time", nullable = false)
    private Double drivingTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private DrivingCity drivingCity;
}

