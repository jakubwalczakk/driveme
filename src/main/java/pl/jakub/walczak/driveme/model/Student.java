package pl.jakub.walczak.driveme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String pesel;
    //  private Address address;
    private Date registrationDate;

    @JsonIgnore
    private String password;
    private int amountOfTakenHours;

    public Student(){

    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String pesel, Address address, Date registrationDate, String password, int amountOfTakenHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
        //   this.address = address;
        this.registrationDate = registrationDate;
        this.password = password;
        this.amountOfTakenHours = amountOfTakenHours;
    }

    public Student(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPesel() {
        return pesel;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getPassword() {
        return password;
    }

    public int getAmountOfTakenHours() {
        return amountOfTakenHours;
    }
}
