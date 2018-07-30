package pl.jakub.walczak.driveme.model.course;

import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "courses")
public class Course {


    private static final double price = 1500.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", nullable = false)
    private Date startDate;
    @Column(name = "driving_hours", nullable = false)
    private int drivingHours;
    @Column(name = "lecture_hours", nullable = false)
    private int lectureHours;
    @Column(name = "taken_driving_hours", nullable = false)
    private int takenDrivingHours;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TheoreticalExam> theoreticalExams;

    @OneToOne(cascade = CascadeType.ALL)
    private PracticalExam practicalExam;

    public Course() {
    }

    public Course(Date startDate, int drivingHours, int lectureHours, int takenDrivingHours,
                  List<TheoreticalExam> theoreticalExams, PracticalExam practicalExam) {
        this.startDate = startDate;
        this.drivingHours = drivingHours;
        this.lectureHours = lectureHours;
        this.takenDrivingHours = takenDrivingHours;
        this.theoreticalExams = theoreticalExams;
        this.practicalExam = practicalExam;
    }

    public static double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDrivingHours() {
        return drivingHours;
    }

    public void setDrivingHours(int drivingHours) {
        this.drivingHours = drivingHours;
    }

    public int getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(int lectureHours) {
        this.lectureHours = lectureHours;
    }

    public int getTakenDrivingHours() {
        return takenDrivingHours;
    }

    public void setTakenDrivingHours(int takenDrivingHours) {
        this.takenDrivingHours = takenDrivingHours;
    }

    public List<TheoreticalExam> getTheoreticalExams() {
        return theoreticalExams;
    }

    public void setTheoreticalExams(List<TheoreticalExam> theoreticalExams) {
        this.theoreticalExams = theoreticalExams;
    }

    public PracticalExam getPracticalExam() {
        return practicalExam;
    }

    public void setPracticalExam(PracticalExam practicalExam) {
        this.practicalExam = practicalExam;
    }
}
