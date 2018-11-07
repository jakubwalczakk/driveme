package pl.jakub.walczak.driveme.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.mappers.payment.PaymentMapper;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.payment.PaymentRepository;
import pl.jakub.walczak.driveme.services.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private static final int COURSE_PRICE = 1500;

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private UserService userService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.userService = userService;
    }

    // -- methods for controller --
    public Payment addPayment(PaymentDTO paymentDTO) {

        Student student = (Student) userService.mapUserBasicDTOToModel(paymentDTO.getStudent());
        Course course = student.getCourse();

        if(course.getCurrentPayment()==COURSE_PRICE){
            throw new IllegalStateException("Cannot ADD payment, because you can't pay more than the course price = " + COURSE_PRICE);
        }
        Payment payment = mapDTOToModel(paymentDTO, Payment.builder().build());
        course.getPayments().add(payment);
        Double actualSumOfPayments = course.getCurrentPayment();
        course.setCurrentPayment(actualSumOfPayments + payment.getAmount());
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        Optional<Payment> paymentToDelete = paymentRepository.findById(id);
        if (paymentToDelete.isPresent()) {
            paymentRepository.delete(paymentToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE payment with given id = " + id);
        }
    }

    public PaymentDTO getPayment(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return mapModelToDTO(optionalPayment.get(), PaymentDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET payment with given id = " + id);
        }
    }

    public List<PaymentDTO> getAll() {
        return findAll().stream().map(payment -> mapModelToDTO(payment, PaymentDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Set<Payment> findAllById(Set<Long> paymentsToAdd) {
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
