package pl.jakub.walczak.driveme.model.exam;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "exam_date", nullable = false)
    private Instant dateOfExam;
    @Column(name = "activity")
    private Boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
