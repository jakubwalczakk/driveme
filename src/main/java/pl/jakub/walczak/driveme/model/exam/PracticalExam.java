package pl.jakub.walczak.driveme.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.user.Instructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "instructor")
@Entity(name = "practical_exams")
public class PracticalExam extends Exam {

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;
    private final Integer durationTime = 60;

}
