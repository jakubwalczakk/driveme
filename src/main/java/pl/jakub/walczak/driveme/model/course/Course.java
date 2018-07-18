package pl.jakub.walczak.driveme.model.course;

import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Student student;
    @Column(name = "price")
    private double price;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "driving_hours")
    private int drivingHours;
    @Column(name = "lecture_hours")
    private int lectureHours;

}
