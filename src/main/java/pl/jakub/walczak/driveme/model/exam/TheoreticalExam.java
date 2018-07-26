package pl.jakub.walczak.driveme.model.exam;

import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "theoretical_exam")
public class TheoreticalExam extends Exam {

    private static final int MAXIMUM_POINTS_AMOUNT = 74;

    @Column(name = "scored_points", nullable = false)
    private int scoredPoints;

    @Column(name = "result", nullable = false)
    private double result;

    @Column(name = "status", nullable = false)
    private boolean status;

    public TheoreticalExam() {

    }

    public TheoreticalExam(Date dateOfExam, Student student, int scoredPoints) {
        super(dateOfExam, student);
        this.scoredPoints = scoredPoints;
        this.result = scoredPoints * 1.0 / MAXIMUM_POINTS_AMOUNT;
    }

    public TheoreticalExam(int scoredPoints, double result, boolean status) {
        this.scoredPoints = scoredPoints;
        this.result = result;
        this.status = status;
    }

    public TheoreticalExam(Date dateOfExam, Student student, int scoredPoints, double result, boolean status) {
        super(dateOfExam, student);
        this.scoredPoints = scoredPoints;
        this.result = result;
        this.status = status;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public void setScoredPoints(int scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public static int getMaximumPointsAmount() {
        return MAXIMUM_POINTS_AMOUNT;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
