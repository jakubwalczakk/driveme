package pl.jakub.walczak.driveme.model;

import pl.jakub.walczak.driveme.enums.GasType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String licensePlateNumber;
    private String brand;
    private GasType gasType;

}
