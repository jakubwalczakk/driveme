package pl.jakub.walczak.driveme.model.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.user.User;

import javax.persistence.*;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "exam_date", nullable = false)
    private Instant dateOfExam;
    @Column(name = "activity")
    private Boolean active;
    @ManyToOne
    private User student;

}
