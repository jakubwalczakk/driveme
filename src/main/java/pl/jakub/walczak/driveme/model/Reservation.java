package pl.jakub.walczak.driveme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int studentId;
    private int instructorId;
    private int carId;
    private Date term;
    private double drivingTime;
    private String drivingCity;
}
