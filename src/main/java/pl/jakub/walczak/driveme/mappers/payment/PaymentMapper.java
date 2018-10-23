package pl.jakub.walczak.driveme.mappers.payment;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.model.payment.Payment;

@Component
public class PaymentMapper {

    public PaymentDTO mapModelToDTO(Payment model, PaymentDTO dto) {
        dto.setId(model.getId());
        dto.setDate(model.getDate());
        dto.setAmount(model.getAmount());
        return dto;
    }

    public Payment mapDTOToModel(PaymentDTO dto, Payment model) {
        model.setId(dto.getId());
        model.setDate(dto.getDate());
        model.setAmount(dto.getAmount());
        return model;
    }
}
