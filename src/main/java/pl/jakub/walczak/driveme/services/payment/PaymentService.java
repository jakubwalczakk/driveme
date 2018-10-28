package pl.jakub.walczak.driveme.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.mappers.payment.PaymentMapper;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.repos.payment.PaymentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    // -- methods for controller --
    public Payment addPayment(PaymentDTO paymentDTO) {
        Payment payment = mapDTOToModel(paymentDTO, Payment.builder().build());
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        Optional<Payment> paymentToDelete = paymentRepository.findById(id);
        if (paymentToDelete.isPresent()) {
            paymentRepository.delete(paymentToDelete.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public PaymentDTO getPayment(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return mapModelToDTO(optionalPayment.get(), PaymentDTO.builder().build());
        } else {
            throw new NoSuchElementException();
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
