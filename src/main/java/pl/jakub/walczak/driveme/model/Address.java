package pl.jakub.walczak.driveme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String city;
    private String zipCode;
    private String street;
    private int houseNumber;
    private int localNumber;
}
