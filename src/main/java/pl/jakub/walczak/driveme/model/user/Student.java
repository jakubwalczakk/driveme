package pl.jakub.walczak.driveme.model.user;

import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.reservation.Reservation;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "students")
public class Student extends User {

    @Column(name = "pesel", nullable = false)
    private String pesel;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Address address;
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<Payment> payments;
    @OneToOne(cascade = CascadeType.ALL)
    private Course course;
    @OneToMany(mappedBy = "student")
    private Set<Reservation> reservations;

    public Student() {

    }

    public Student(String name, String surname, String phoneNumber, String email, String password, String pesel,
                   Address address, Date registrationDate, Set<Payment> payments, Course course) {
        super(name, surname, phoneNumber, email, password);
        this.pesel = pesel;
        this.address = address;
        this.registrationDate = registrationDate;
        this.payments = payments;
        this.course = course;
    }

    public Student(String name, String surname, String phoneNumber, String email, String password, String pesel,
                   Address address, Date date) {
        super(name, surname, phoneNumber, email, password);
        this.pesel = pesel;
        this.address = address;
        this.registrationDate = date;
    }

    public Student(String name, String surname, String phoneNumber, String email, String password, String pesel,
                   Address address, Date registrationDate, Set<Payment> payments) {
        super(name, surname, phoneNumber, email, password);
        this.pesel = pesel;
        this.address = address;
        this.registrationDate = registrationDate;
        this.payments = payments;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
