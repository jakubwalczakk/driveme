package pl.jakub.walczak.driveme.mappers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.services.address.AddressService;
import pl.jakub.walczak.driveme.services.course.CourseService;
import pl.jakub.walczak.driveme.services.event.DrivingService;
import pl.jakub.walczak.driveme.services.event.ReservationService;

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
        dto.setUserRole(model.getUserRole().toString());
        dto.setActive(model.getActive());
        dto.setPesel(model.getPesel());
        dto.setAddress(addressService.mapModelToDTO(model.getAddress(), AddressDTO.builder().build()));
        dto.setRegistrationDate(model.getRegistrationDate());
        dto.setCourse(courseService.mapModelToDTO(model.getCourse(), CourseDTO.builder().build()));
        return dto;
    }

    //TODO
    public Student mapDTOToModel(StudentDTO dto, Student model) {
        return model;
    }
}
