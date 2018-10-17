package pl.jakub.walczak.driveme.mappers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.services.address.AddressService;
import pl.jakub.walczak.driveme.services.course.CourseService;
import pl.jakub.walczak.driveme.services.event.DrivingService;
import pl.jakub.walczak.driveme.services.event.ReservationService;

import java.util.stream.Collectors;

@Slf4j
@Component
public class StudentMapper {

    private AddressService addressService;
    private ReservationService reservationService;
    private DrivingService drivingService;
    private CourseService courseService;

    public StudentMapper(AddressService addressService, ReservationService reservationService, DrivingService drivingService, CourseService courseService) {
        this.addressService = addressService;
        this.reservationService = reservationService;
        this.drivingService = drivingService;
        this.courseService = courseService;
    }

    public StudentDTO mapModelToDTO(Student model, StudentDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setPassword(model.getPassword());
        dto.setUserRole(model.getUserRole().toString());
        dto.setActive(model.getActive());
        dto.setPesel(model.getPesel());
        dto.setAddress(addressService.mapModelToDTO(model.getAddress(), AddressDTO.builder().build()));
        dto.setRegistrationDate(model.getRegistrationDate());
        dto.setCourse(courseService.mapModelToDTO(model.getCourse(), CourseDTO.builder().build()));

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
    public Student mapDTOToModel(StudentDTO dto, Student model) {
        return model;
    }
}
