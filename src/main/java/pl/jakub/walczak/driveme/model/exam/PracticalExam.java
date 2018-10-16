package pl.jakub.walczak.driveme.model.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.user.Instructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(exclude="instructor")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "practical_exam")
public class PracticalExam extends Exam {

    @ManyToOne//(cascade = CascadeType.ALL)
    private Car car;
    @ManyToOne//(cascade = CascadeType.ALL)
    private Instructor instructor;

}
