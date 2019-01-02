package pl.jakub.walczak.driveme.model.event.exam;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.enums.EventType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "theoretical_exams")
public class TheoreticalExam extends Exam {

    public static final Integer MAXIMUM_POINTS_AMOUNT = 74;

    @Column(name = "scored_points", nullable = false)
    private Integer scoredPoints;
    @Column(name = "result", nullable = false)
    private Double result;
    private final EventType eventType = EventType.THEORETICAL_EXAM;

}
