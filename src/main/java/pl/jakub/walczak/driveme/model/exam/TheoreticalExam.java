package pl.jakub.walczak.driveme.model.exam;

import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "theoretical_exam")
public class TheoreticalExam extends Exam {

    @Column(name = "scored_points")
    private int scoredPoints;

    @Column(name="maximum_points")
    private static final int MAXIMUM_POINTS_AMOUNT=74;

    @Column(name="result")
    private double result;

    public TheoreticalExam(){

    }

    public TheoreticalExam(Date dateOfExam, Student student, int scoredPoints) {
        super(dateOfExam, student);
        this.scoredPoints = scoredPoints;
        this.result=scoredPoints*1.0/MAXIMUM_POINTS_AMOUNT;
    }
}
