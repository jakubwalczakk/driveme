package pl.jakub.walczak.driveme.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.payment.PaymentRepository;
import pl.jakub.walczak.driveme.utils.Generator;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class PaymentRepositoryTest {

    private final static Random RANDOM = new Random();

    @TestConfiguration
    static class CarRepositoryTestContextConfiguration {
        @Bean
        public Generator generator() {
            return new Generator();
        }
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private Generator generator;

    @Test
    public void whenFindAllPaymentsByIdsThenReturnPayments() {

        // given
        Instant paymentDate = Instant.now();
        Double amount = 100.0;
        Double amount2 = 400.0;
        Double amount3 = 259.2;

        Payment payment = Payment.builder().date(paymentDate).amount(amount).build();
        Long id1 = entityManager.persistAndFlush(payment).getId();
        Payment payment2 = Payment.builder().date(paymentDate).amount(amount2).build();
        Long id2 = entityManager.persistAndFlush(payment2).getId();
        Payment payment3 = Payment.builder().date(paymentDate).amount(amount3).build();
        Long id3 = entityManager.persistAndFlush(payment3).getId();

        Set<Long> ids = new HashSet<>();
        ids.add(id1);
        ids.add(id2);

        //when
        final List<Payment> payments = paymentRepository.findAllByIdIn(ids);

        // then
        assertThat(payments.size()).isEqualTo(2);
        assertThat(payments.contains(payment)).isEqualTo(true);
        assertThat(payments.contains(payment3)).isEqualTo(false);
        assertThat(payments.contains(null)).isEqualTo(false);
    }

    @Test
    public void whenFindAllPaymentsByStudentIdThenReturnPayments() {

        // given
        Student student = Student.builder()
                .name("Jan").surname("Test").email("jan.test@driveme.pl").password(generator.generatePassword())
                .phoneNumber(generator.generatePhoneNumber()).userRole(UserRole.STUDENT)
                .pesel(generator.generatePesel()).active(true)
                .registrationDate(Instant.now())
                .build();
        Long studentId  = entityManager.persistAndFlush(student).getId();

        Instant paymentDate = Instant.now();
        Double amount = 100.0;
        Double amount2 = 400.0;
        Double amount3 = 259.2;

        Payment payment = Payment.builder().date(paymentDate).student(student).amount(amount).build();
        entityManager.persistAndFlush(payment);
        Payment payment2 = Payment.builder().date(paymentDate).student(student).amount(amount2).build();
        entityManager.persistAndFlush(payment2);
        Payment payment3 = Payment.builder().date(paymentDate).student(student).amount(amount3).build();
        entityManager.persistAndFlush(payment3);

        //when
        final List<Payment> payments = paymentRepository.findAllByStudentIdOrderByDateDesc(studentId);

        // then
        assertThat(payments.size()).isEqualTo(3);
        assertThat(payments.contains(payment)).isEqualTo(true);
        assertThat(payments.contains(null)).isEqualTo(false);
    }
}
