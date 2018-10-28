package pl.jakub.walczak.driveme.repos.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.payment.Payment;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Set<Payment> findAllById(Set<Long> ids);
}
