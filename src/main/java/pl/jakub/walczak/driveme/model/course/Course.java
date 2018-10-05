package pl.jakub.walczak.driveme.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "courses")
public class Course {

    //private final Double price = 1500.0;

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
    //    @Column(name = "current_payment")
//    private Double currentPayment;
//    @OneToOne//(cascade = CascadeType.ALL)
//    private Student student;
    @OneToMany(cascade = CascadeType.ALL)
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<TheoreticalExam> theoreticalExams;
    @OneToOne(cascade = CascadeType.ALL)
    private PracticalExam practicalExam;

}
