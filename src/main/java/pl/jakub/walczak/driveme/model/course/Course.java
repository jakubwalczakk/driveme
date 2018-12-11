package pl.jakub.walczak.driveme.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.payment.Payment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "courses")
public class Course {

    private static final Double course_price = 1500.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "driving_hours", nullable = false)
    private final Integer drivingHours = 32;
    @Column(name = "lecture_hours", nullable = false)
    private final Integer lectureHours = 30;
    @Column(name = "taken_driving_hours", nullable = false)
    private Integer takenDrivingHours;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Payment> payments;
    private Double currentPayment;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Reservation> reservations;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Driving> drivings;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TheoreticalExam> theoreticalExams;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PracticalExam practicalExam;
    @Enumerated
    @Column(name = "status", nullable = false)
    private CourseStatus status;
}
