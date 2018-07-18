package pl.jakub.walczak.driveme.repos.payment;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.payment.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
