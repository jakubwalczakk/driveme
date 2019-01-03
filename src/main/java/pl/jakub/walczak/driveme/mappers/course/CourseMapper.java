package pl.jakub.walczak.driveme.mappers.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.event.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;
import pl.jakub.walczak.driveme.services.event.DrivingService;
import pl.jakub.walczak.driveme.services.event.ReservationService;
import pl.jakub.walczak.driveme.services.event.exam.PracticalExamService;
import pl.jakub.walczak.driveme.services.event.exam.TheoreticalExamService;
import pl.jakub.walczak.driveme.services.payment.PaymentService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private PracticalExamService practicalExamService;
    private TheoreticalExamService theoreticalExamService;
    private PaymentService paymentService;
    private DrivingService drivingService;
    private ReservationService reservationService;

    @Autowired
    public CourseMapper(PracticalExamService practicalExamService, TheoreticalExamService theoreticalExamService,
                        PaymentService paymentService, DrivingService drivingService, ReservationService reservationService) {
        this.practicalExamService = practicalExamService;
        this.theoreticalExamService = theoreticalExamService;
        this.paymentService = paymentService;
        this.drivingService = drivingService;
        this.reservationService = reservationService;
    }

    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        dto.setId(model.getId());
        dto.setStartDate(model.getStartDate());
        dto.setTakenDrivingHours(model.getTakenDrivingHours());
        dto.setPracticalExam(practicalExamService.mapModelToDTO(model.getPracticalExam(), PracticalExamDTO.builder().build()));
        dto.setTheoreticalExams(model.getTheoreticalExams().stream()
                .map(exam -> theoreticalExamService.mapModelToDTO(exam, TheoreticalExamDTO.builder().build()))
                .collect(Collectors.toList()));
        dto.setPayments(model.getPayments().stream()
                .map(payment -> paymentService.mapModelToDTO(payment, PaymentDTO.builder().build()))
                .collect(Collectors.toList()));
        dto.setCurrentPayment(model.getCurrentPayment());
        dto.setDrivings(model.getDrivings().stream()
                .map(driving -> drivingService.mapModelToDTO(driving, DrivingDTO.builder().build()))
                .collect(Collectors.toList()));
        dto.setReservations(model.getReservations().stream()
                .map(reservation -> reservationService.mapModelToDTO(reservation, ReservationDTO.builder().build()))
                .collect(Collectors.toList()));
        dto.setStatus(model.getStatus().getValue());
        return dto;
    }

    public Course mapDTOToModel(CourseDTO dto, Course model) {
        model.setId(dto.getId());
        model.setStartDate(dto.getStartDate() == null ? model.getStartDate() : dto.getStartDate());
        model.setTakenDrivingHours(dto.getTakenDrivingHours() == null ? model.getTakenDrivingHours() : dto.getTakenDrivingHours());

        PracticalExamDTO practicalExamDTO = dto.getPracticalExam();
        if (practicalExamDTO != null) {
            Optional<PracticalExam> optionalPracticalExam = practicalExamService.findById(practicalExamDTO.getId());
            model.setPracticalExam(optionalPracticalExam.orElse(model.getPracticalExam()));
        }

        Set<Long> theoreticalExamsToAdd = dto.getTheoreticalExams().stream().map(exam -> exam.getId())
                .collect(Collectors.toSet());
        model.setTheoreticalExams(theoreticalExamService.findAllById(theoreticalExamsToAdd));
        Set<Long> paymentsToAdd = dto.getPayments().stream().map(payment -> payment.getId())
                .collect(Collectors.toSet());
        model.setPayments(paymentService.findAllById(paymentsToAdd));
        model.setCurrentPayment(dto.getCurrentPayment());
        Set<Long> drivingsToAdd = dto.getDrivings().stream().map(driving -> driving.getId())
                .collect(Collectors.toSet());
        model.setDrivings(drivingService.findAllById(drivingsToAdd));
        Set<Long> reservationsToAdd = dto.getReservations().stream().map(reservation -> reservation.getId())
                .collect(Collectors.toSet());
        model.setReservations(reservationService.findAllById(reservationsToAdd));

        try {
            model.setStatus(dto.getStatus() == null ? model.getStatus() : CourseStatus.of(dto.getStatus()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setStatus(CourseStatus.DEFAULT);
        }
        return model;
    }
}
