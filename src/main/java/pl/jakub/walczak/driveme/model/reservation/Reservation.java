package pl.jakub.walczak.driveme.model.reservation;

import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Student student;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Instructor instructor;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Car car;
    @Column(name="reservation_date")
    private Date date;
    @Column(name = "driving_time")
    private double drivingTime;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DrivingCity drivingCity;

    public Reservation(){

    }

    public Reservation(Student student, Instructor instructor, Car car, Date date, double drivingTime, DrivingCity drivingCity) {
        this.student = student;
        this.instructor = instructor;
        this.car = car;
        this.date = date;
        this.drivingTime = drivingTime;
        this.drivingCity = drivingCity;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(double drivingTime) {
        this.drivingTime = drivingTime;
    }

    public DrivingCity getDrivingCity() {
        return drivingCity;
    }

    public void setDrivingCity(DrivingCity drivingCity) {
        this.drivingCity = drivingCity;
    }
}
