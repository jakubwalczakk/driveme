package pl.jakub.walczak.driveme.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "theoretical_exam")
public class TheoreticalExam extends Exam {

    private static final Integer MAXIMUM_POINTS_AMOUNT = 74;

    @Column(name = "scored_points", nullable = false)
    private Integer scoredPoints;

    @Column(name = "result", nullable = false)
    private Double result;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public TheoreticalExam() {

    }

    public TheoreticalExam(Date dateOfExam, Integer scoredPoints) {
        super(dateOfExam);
        this.scoredPoints = scoredPoints;
        this.result = scoredPoints * 1.0 / MAXIMUM_POINTS_AMOUNT;
    }

    public TheoreticalExam(Date dateOfExam, Integer scoredPoints, Boolean status) {
        super(dateOfExam);
        this.scoredPoints = scoredPoints;
        this.status = status;
    }

    public static Integer getMaximumPointsAmount() {
        return MAXIMUM_POINTS_AMOUNT;
    }

    public Integer getScoredPoints() {
        return scoredPoints;
    }

    public void setScoredPoints(Integer scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
