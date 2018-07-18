package pl.jakub.walczak.driveme.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.address.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity(name = "students")
public class Student extends User {

    @Column(name = "pesel")
    private String pesel;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Address address;
    @Column(name = "registration_date")
    private Date registrationDate;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Payment> payments;
    @Column(name="paid_amount")
    private double paidAmount;
    @Column(name = "amount_to_paid")
    private double amountToPaid;

//    @JsonIgnore
//    @Transient
//    @NotEmpty
//    @Length(min = 5)
//    @Column(name = "password")
//    private String password;

    @Column(name="taken_hours")
    private int takenHours;

    public Student(){

    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String pesel, double paidAmount, double
            amountToPaid) {
        super(firstName, lastName, phoneNumber, email);
        this.pesel = pesel;
        this.paidAmount = paidAmount;
        this.amountToPaid = amountToPaid;
    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String pesel) {
        super(firstName, lastName, phoneNumber, email);
        this.pesel=pesel;
    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String pesel, Address address) {
        super(firstName, lastName, phoneNumber, email);
        this.pesel=pesel;
        this.address=address;
    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String pesel, Address address, Date
            registrationDate, List<Payment> payments, double paidAmount, double amountToPaid, @NotEmpty @Length(min = 5) String
            password, int takenHours) {
        super(firstName, lastName, phoneNumber, email);
        this.pesel = pesel;
        this.address = address;
        this.registrationDate = registrationDate;
        this.payments = payments;
        this.paidAmount = paidAmount;
        this.amountToPaid = amountToPaid;
        //this.password = password;
        this.takenHours = takenHours;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getAmountToPaid() {
        return amountToPaid;
    }

    public void setAmountToPaid(double amountToPaid) {
        this.amountToPaid = amountToPaid;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public int getTakenHours() {
        return takenHours;
    }

    public void setTakenHours(int takenHours) {
        this.takenHours = takenHours;
    }
}
