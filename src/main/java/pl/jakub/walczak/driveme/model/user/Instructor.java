package pl.jakub.walczak.driveme.model.user;

import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.reservation.Reservation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "instructors")
public class Instructor extends User {

    @Column(name = "available_hours")
    private Integer availableHours;
    @Column(name = "taken_hours")
    private Integer takenHours;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Course> courses;
    @OneToMany(mappedBy = "instructor")
    private Set<PracticalExam>practicalExams;
    @OneToMany(mappedBy = "instructor")
    private Set<Reservation>reservations;

    public Instructor() {

    }

    public Instructor(String name, String surname, String phoneNumber, String email, String password, int availableHours, int
            takenHours) {
        super(name, surname, phoneNumber, email, password);
        this.availableHours = availableHours;
        this.takenHours = takenHours;
    }

    public Integer getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(Integer availableHours) {
        this.availableHours = availableHours;
    }

    public Integer getTakenHours() {
        return takenHours;
    }

    public void setTakenHours(Integer takenHours) {
        this.takenHours = takenHours;
    }
}
