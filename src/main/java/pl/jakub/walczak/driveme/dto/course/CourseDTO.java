package pl.jakub.walczak.driveme.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.payment.Payment;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
    private LocalDate startDate;
    private Integer takenDrivingHours;
    private UserBasicDTO student;
    private PracticalExamDTO practicalExam;
    private Set<TheoreticalExamDTO> theoreticalExams;
    private Set<PaymentDTO> payments;
    private String status;
    private Double currentPayment;
    private Set<ReservationDTO> reservations;
    private Set<DrivingDTO> drivings;
}
