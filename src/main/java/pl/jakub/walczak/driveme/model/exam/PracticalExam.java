package pl.jakub.walczak.driveme.model.exam;

import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.car.Car;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity(name = "practical_exam")
public class PracticalExam extends Exam {

    @ManyToOne(cascade = {CascadeType.ALL})
    private Car car;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Instructor instructor;

    public PracticalExam() {
    }

    public PracticalExam(Car car, Instructor instructor) {
        this.car = car;
        this.instructor = instructor;
    }

    public PracticalExam(Date dateOfExam, Student student, Car car, Instructor instructor) {
        super(dateOfExam, student);
        this.car = car;
        this.instructor = instructor;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
