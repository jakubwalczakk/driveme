package pl.jakub.walczak.driveme.model.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.course.Course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "theoretical_exam")
public class TheoreticalExam extends Exam {

    private static final Integer MAXIMUM_POINTS_AMOUNT = 74;

    @Column(name = "scored_points", nullable = false)
    private Integer scoredPoints;
    @Column(name = "result", nullable = false)
    private Double result;
    @Column(name = "status", nullable = false)
    private Boolean status;
    @ManyToOne
    private Course course;

}
