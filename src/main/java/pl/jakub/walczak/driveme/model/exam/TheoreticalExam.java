package pl.jakub.walczak.driveme.model.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "theoretical_exam")
public class TheoreticalExam extends Exam {

    public static final Integer MAXIMUM_POINTS_AMOUNT = 74;

    @Column(name = "scored_points", nullable = false)
    private Integer scoredPoints;
    @Column(name = "result", nullable = false)
    private Double result;
    @Column(name = "status", nullable = false)
    private Boolean passed;

}
