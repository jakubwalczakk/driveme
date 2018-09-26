package pl.jakub.walczak.driveme.model.course;

import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "courses")
public class Course {

    private static final Double price = 1500.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "driving_hours", nullable = false)
    private Integer drivingHours;
    @Column(name = "lecture_hours", nullable = false)
    private Integer lectureHours;
    @Column(name = "taken_driving_hours", nullable = false)
    private Integer takenDrivingHours;
    @Column(name = "current_payment")
    private Double currentPayment;
    @OneToOne(cascade = CascadeType.ALL)
    private Student student;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<TheoreticalExam> theoreticalExams;
    @OneToOne(cascade = CascadeType.ALL)
    private PracticalExam practicalExam;

    public Course() {
    }

    public Course(LocalDate startDate, Integer drivingHours, Integer lectureHours, Integer takenDrivingHours,
                  List<TheoreticalExam> theoreticalExams, PracticalExam practicalExam) {
        this.startDate = startDate;
        this.drivingHours = drivingHours;
        this.lectureHours = lectureHours;
        this.takenDrivingHours = takenDrivingHours;
        this.theoreticalExams = theoreticalExams;
        this.practicalExam = practicalExam;
    }

    public static Double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDrivingHours() {
        return drivingHours;
    }

    public void setDrivingHours(Integer drivingHours) {
        this.drivingHours = drivingHours;
    }

    public Integer getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Integer lectureHours) {
        this.lectureHours = lectureHours;
    }

    public Integer getTakenDrivingHours() {
        return takenDrivingHours;
    }

    public void setTakenDrivingHours(Integer takenDrivingHours) {
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
