package pl.jakub.walczak.driveme.model.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //FIXME
    //it must be contained
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Student student;
    @Column(name = "payment_date")
    private Instant date;
    @Column(name = "amount", nullable = false)
    private Double amount;
}
