package pl.jakub.walczak.driveme.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @Column(name = "status", nullable = false)
    private Boolean passed;
    @Column(name = "activity")
    private Boolean active;
}
