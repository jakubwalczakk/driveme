package pl.jakub.walczak.driveme.model.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = "student")
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
//    @JsonBackReference
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Student student;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Payment> payments;
    private Double currentPayment;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Reservation> reservations;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Driving> drivings;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TheoreticalExam> theoreticalExams;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PracticalExam practicalExam;
    @Enumerated
    @Column(name = "status", nullable = false)
    private CourseStatus status;
}
