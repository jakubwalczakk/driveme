package pl.jakub.walczak.driveme.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "events")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne//(cascade = CascadeType.ALL)
    private Instructor instructor;
    @ManyToOne//(cascade = CascadeType.ALL)
    private Car car;
    @Column(name = "event_date", nullable = false)
    private Instant date;
    @Column(name = "event_duration", nullable = false)
    private Double eventDuration;
    @ManyToOne//(cascade = CascadeType.ALL)
    private DrivingCity drivingCity;
}
