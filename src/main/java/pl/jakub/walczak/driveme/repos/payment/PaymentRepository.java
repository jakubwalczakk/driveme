package pl.jakub.walczak.driveme.repos.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.payment.Payment;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllById(Set<Long> ids);
    List<Payment> findAllByStudentIdOrderByDate(Long studentId);
}
