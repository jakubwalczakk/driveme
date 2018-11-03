package pl.jakub.walczak.driveme.mappers.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.dto.payment.PaymentDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.event.DrivingService;
import pl.jakub.walczak.driveme.services.event.ReservationService;
import pl.jakub.walczak.driveme.services.exam.PracticalExamService;
import pl.jakub.walczak.driveme.services.exam.TheoreticalExamService;
import pl.jakub.walczak.driveme.services.payment.PaymentService;
import pl.jakub.walczak.driveme.services.user.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private PracticalExamService practicalExamService;
    private TheoreticalExamService theoreticalExamService;
    private PaymentService paymentService;
    private DrivingService drivingService;
    private ReservationService reservationService;
    private UserService userService;

    @Autowired
    public CourseMapper(PracticalExamService practicalExamService, TheoreticalExamService theoreticalExamService,
                        PaymentService paymentService, DrivingService drivingService, ReservationService reservationService,
                        UserService userService) {
        this.practicalExamService = practicalExamService;
        this.theoreticalExamService = theoreticalExamService;
        this.paymentService = paymentService;
        this.drivingService = drivingService;
        this.reservationService = reservationService;
        this.userService = userService;
    }

    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        dto.setId(model.getId());
//        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(model.getStartDate());
        dto.setTakenDrivingHours(model.getTakenDrivingHours());
        dto.setPracticalExam(practicalExamService.mapModelToDTO(model.getPracticalExam(), PracticalExamDTO.builder().build()));
        dto.setTheoreticalExams(model.getTheoreticalExams().stream()
                .map(exam -> theoreticalExamService.mapModelToDTO(exam, TheoreticalExamDTO.builder().build()))
                .collect(Collectors.toSet()));
        dto.setPayments(model.getPayments().stream()
                .map(payment -> paymentService.mapModelToDTO(payment, PaymentDTO.builder().build()))
                .collect(Collectors.toSet()));
        dto.setCurrentPayment(model.getCurrentPayment());
        dto.setDrivings(model.getDrivings().stream()
                .map(driving -> drivingService.mapModelToDTO(driving, DrivingDTO.builder().build()))
                .collect(Collectors.toSet()));
        dto.setReservations(model.getReservations().stream()
                .map(reservation -> reservationService.mapModelToDTO(reservation, ReservationDTO.builder().build()))
                .collect(Collectors.toSet()));
        dto.setStatus(model.getStatus().toString());
        return dto;
    }

    public Course mapDTOToModel(CourseDTO dto, Course model) {
        model.setId(dto.getId());
//        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
//        if (student instanceof Student) {
//            model.setStudent((Student) student);
//        }
        model.setStartDate(dto.getStartDate());
        model.setTakenDrivingHours(dto.getTakenDrivingHours());
        model.setPracticalExam(practicalExamService.mapDTOToModel(dto.getPracticalExam(), model.getPracticalExam()));
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
            model.setStatus(CourseStatus.valueOf(dto.getStatus().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setStatus(CourseStatus.DEFAULT);
        }
        return model;
    }
}
