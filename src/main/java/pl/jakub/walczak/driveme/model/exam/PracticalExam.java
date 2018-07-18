package pl.jakub.walczak.driveme.model.exam;

import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.car.Car;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "practical_exam")
public class PracticalExam extends Exam {

    @ManyToOne
    private Car car;

    public PracticalExam() {
    }

    public PracticalExam(Date dateOfExam, Student student, Car car) {
        super(dateOfExam, student);
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
