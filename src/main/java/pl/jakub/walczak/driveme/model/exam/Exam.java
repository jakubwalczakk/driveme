package pl.jakub.walczak.driveme.model.exam;

import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "exam_date")
    private Date dateOfExam;

    @OneToOne
    private Student student;

    public Exam(){

    }

    public Exam(Date dateOfExam, Student student) {
        this.dateOfExam = dateOfExam;
        this.student = student;
    }
}
