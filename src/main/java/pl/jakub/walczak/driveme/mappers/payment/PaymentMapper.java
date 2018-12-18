package pl.jakub.walczak.driveme.mappers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

import java.util.Optional;

@Component
public class PaymentMapper {

    private UserService userService;

    @Autowired
    public PaymentMapper(UserService userService) {
        this.userService = userService;
    }

    public PaymentDTO mapModelToDTO(Payment model, PaymentDTO dto) {
        dto.setId(model.getId());
        dto.setDate(DateFormatter.formatDateToString(model.getDate()));
        dto.setAmount(model.getAmount());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        return dto;
    }

    public Payment mapDTOToModel(PaymentDTO dto, Payment model) {
        model.setId(dto.getId());
        model.setDate(DateFormatter.parseStringToInstant(dto.getDate()));
        model.setAmount(dto.getAmount());

        UserBasicDTO studentDTO = dto.getStudent();
        if (studentDTO != null) {
            Optional<User> optionalUser = userService.findById(studentDTO.getId());
            if (optionalUser.isPresent()) {
                Student student = (Student) optionalUser.get();
                model.setStudent(student);
            }
        }
        return model;
    }
}
