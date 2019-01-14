package pl.jakub.walczak.driveme.model.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.enums.EventType;
import pl.jakub.walczak.driveme.model.car.Car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "instructor")
@Entity(name = "practical_exams")
public class PracticalExam extends Event {

    @Column(name = "passed", nullable = false)
    private Boolean passed;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    private final Integer duration = 60;
    private final EventType eventType = EventType.PRACTICAL_EXAM;

}
