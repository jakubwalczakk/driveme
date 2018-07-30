package pl.jakub.walczak.driveme.model.exam;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_date", nullable = false)
    private Date dateOfExam;

    public Exam() {

    }

    public Exam(Date dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(Date dateOfExam) {
        this.dateOfExam = dateOfExam;
    }
}
