package pl.jakub.walczak.driveme.services.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.mappers.payment.PaymentMapper;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.payment.PaymentRepository;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaymentService {

    private static final int COURSE_PRICE = 1500;

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private UserService userService;
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, UserService userService, AuthenticationUtil authenticationUtil) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.userService = userService;
        this.authenticationUtil = authenticationUtil;
    }

    // -- methods for controller --
    public Payment addPayment(PaymentDTO paymentDTO) {
        log.info("Adding new Payment...");
        Student student = (Student) userService.mapUserBasicDTOToModel(paymentDTO.getStudent());
        Course course = student.getCourse();

        if (course.getCurrentPayment() == COURSE_PRICE) {
            throw new IllegalStateException("Cannot ADD Payment, because you can't pay more than the course price = " + COURSE_PRICE);
        }
        Payment payment = mapDTOToModel(paymentDTO, Payment.builder().build());
        course.getPayments().add(payment);
        Double actualSumOfPayments = course.getCurrentPayment();
        course.setCurrentPayment(actualSumOfPayments + payment.getAmount());
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        log.info("Deleting the Payment with id = " + id);
        Optional<Payment> paymentToDelete = paymentRepository.findById(id);
        if (paymentToDelete.isPresent()) {
            paymentRepository.delete(paymentToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE Payment with given id = " + id);
        }
    }

    public PaymentDTO getPayment(Long id) {
        log.info("Getting the Payment with id = " + id);
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return mapModelToDTO(optionalPayment.get(), PaymentDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET Payment with given id = " + id);
        }
    }

    public List<PaymentDTO> getPaymentsByStudent() {

        User currentUser = authenticationUtil.getCurrentLoggedUser();
        Long currentLoggedUserId = currentUser.getId();
        log.info("Getting all Payments of current logged Student with given id  = " + currentLoggedUserId);

        List<Payment> listOfStudentPayments = paymentRepository.findAllByStudentIdOrderByDateDesc(currentLoggedUserId);
        return listOfStudentPayments.stream()
                .map(payment -> mapModelToDTO(payment, PaymentDTO.builder().build())).collect(Collectors.toList());
    }

    public List<PaymentDTO> getAll() {
        log.info("Getting all Payments");
        return findAll().stream().map(payment -> mapModelToDTO(payment, PaymentDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findAllById(Set<Long> paymentsToAdd) {
        return paymentRepository.findAllById(paymentsToAdd);
    }

    // -- mapper methods --
    public PaymentDTO mapModelToDTO(Payment model, PaymentDTO dto) {
        return paymentMapper.mapModelToDTO(model, dto);
    }

    public Payment mapDTOToModel(PaymentDTO dto, Payment model) {
        if (dto.getId() != null) {
            Optional<Payment> optionalPayment = paymentRepository.findById(dto.getId());
            if (optionalPayment.isPresent()) {
                model = optionalPayment.get();
            }
        }
        model = paymentMapper.mapDTOToModel(dto, model);
        return paymentRepository.save(model);
    }
}
