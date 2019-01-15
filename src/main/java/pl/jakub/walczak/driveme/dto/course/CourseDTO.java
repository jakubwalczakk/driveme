package pl.jakub.walczak.driveme.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private LocalDate startDate;
    private Double takenDrivingHours;
    private List<PaymentDTO> payments;
    private Double currentPayment;
    private PracticalExamDTO practicalExam;
    private List<ReservationDTO> reservations;
    private List<DrivingDTO> drivings;
    private String status;
}
