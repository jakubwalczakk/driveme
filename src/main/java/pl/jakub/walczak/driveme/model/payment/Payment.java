package pl.jakub.walczak.driveme.model.payment;

import pl.jakub.walczak.driveme.model.user.Student;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(cascade = CascadeType.REMOVE)
//    private Student student;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    public Payment() {

    }

    public Payment(double amount, Date paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
