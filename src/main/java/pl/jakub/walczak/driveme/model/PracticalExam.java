package pl.jakub.walczak.driveme.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity(name = "practical_exam")
public class PracticalExam extends Exam {

    @OneToOne
    private Car car;

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
