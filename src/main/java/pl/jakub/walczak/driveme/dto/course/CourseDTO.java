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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;
//    private UserBasicDTO student;
    private LocalDate startDate;
    private Integer takenDrivingHours;
    private List<PaymentDTO> payments;
    private Double currentPayment;
    private PracticalExamDTO practicalExam;
    private List<TheoreticalExamDTO> theoreticalExams;
    private List<ReservationDTO> reservations;
    private List<DrivingDTO> drivings;
    private String status;
}
