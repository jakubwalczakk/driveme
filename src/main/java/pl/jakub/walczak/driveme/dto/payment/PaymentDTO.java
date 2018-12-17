package pl.jakub.walczak.driveme.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private String date;
    private Double amount;
    private UserBasicDTO student;
}
