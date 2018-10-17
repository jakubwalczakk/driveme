package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.services.event.DrivingService;
import pl.jakub.walczak.driveme.services.event.ReservationService;
import pl.jakub.walczak.driveme.services.exam.PracticalExamService;

import java.util.stream.Collectors;

@Component
public class InstructorMapper {

    private PracticalExamService practicalExamService;
    private ReservationService reservationService;
    private DrivingService drivingService;

    @Autowired
    public InstructorMapper(PracticalExamService practicalExamService, ReservationService reservationService, DrivingService drivingService) {
        this.practicalExamService = practicalExamService;
        this.reservationService = reservationService;
        this.drivingService = drivingService;
    }

    public InstructorDTO mapModelToDTO(Instructor model, InstructorDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setPassword(model.getPassword());
        dto.setUserRole(model.getUserRole().toString());
        dto.setActive(model.getActive());
        dto.setAvailableHours(model.getAvailableHours());
        dto.setTakenHours(model.getTakenHours());
        dto.setPracticalExams(model.getPracticalExams().stream()
                .map(exam -> practicalExamService.mapModelToDTO(exam, PracticalExamDTO.builder().build()))
                .collect(Collectors.toSet()));
        //FIXME
        dto.setReservations(model.getReservations().stream()
                .map(reservation -> reservationService.mapModelToDTO(reservation, ReservationDTO.builder().build()))
                .collect(Collectors.toSet()));
        //FIXME
        dto.setDrivings(model.getDrivings().stream()
                .map(driving -> drivingService.mapModelToDTO(driving, DrivingDTO.builder().build()))
                .collect(Collectors.toSet()));
        return dto;
    }

    //TODO
    public Instructor mapDTOToModel(InstructorDTO dto, Instructor model) {
        return model;
    }
}
