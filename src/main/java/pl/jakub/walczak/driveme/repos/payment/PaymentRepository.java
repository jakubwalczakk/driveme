package pl.jakub.walczak.driveme.repos.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.payment.Payment;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
