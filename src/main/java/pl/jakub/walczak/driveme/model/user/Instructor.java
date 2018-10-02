package pl.jakub.walczak.driveme.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.reservation.Reservation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "instructors")
public class Instructor extends User {

    @Column(name = "available_hours")
    private Integer availableHours;
    @Column(name = "taken_hours")
    private Integer takenHours;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Course> courses;
    @OneToMany(mappedBy = "instructor")
    private Set<PracticalExam> practicalExams;
    @OneToMany(mappedBy = "instructor")
    private Set<Reservation> reservations;

}
