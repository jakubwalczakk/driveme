package pl.jakub.walczak.driveme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name="phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "email", unique = true)
    private String email;
    private String pesel;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Address address;
    @Column(name="registration_date")
    private Date registrationDate;

    @JsonIgnore
    @Transient
    @NotEmpty
    @Length(min = 5)
    private String password;
    private int amountOfTakenHours;

    public Student() {

    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String pesel, Address address, Date
            registrationDate, String password, int amountOfTakenHours) {
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
