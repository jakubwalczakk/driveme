package pl.jakub.walczak.driveme.mappers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.services.address.AddressService;
import pl.jakub.walczak.driveme.services.course.CourseService;
import pl.jakub.walczak.driveme.services.event.DrivingService;
import pl.jakub.walczak.driveme.services.event.ReservationService;

import java.util.Optional;

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
        dto.setRegistrationDate(model.getRegistrationDate());
        dto.setAddress(addressService.mapModelToDTO(model.getAddress(), AddressDTO.builder().build()));
        dto.setCourse(courseService.mapModelToDTO(model.getCourse(), CourseDTO.builder().build()));
        return dto;
    }

    public Student mapDTOToModel(StudentDTO dto, Student model) {
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setSurname(dto.getSurname());
        model.setEmail(dto.getEmail());
        model.setPhoneNumber(dto.getPhoneNumber());
        try {
            model.setUserRole(UserRole.valueOf(dto.getUserRole().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        model.setActive(dto.getActive());
        model.setPesel(dto.getPesel());
        model.setRegistrationDate(dto.getRegistrationDate());

        AddressDTO addressDTO = dto.getAddress();
        if (addressDTO != null) {
            Optional<Address> optionalAddress = addressService.findById(addressDTO.getId());
            if (optionalAddress.isPresent()) {
                model.setAddress(optionalAddress.get());
            }
        }

        CourseDTO courseDTO = dto.getCourse();
        if (courseDTO != null) {
            Optional<Course> optionalCourse = courseService.findById(courseDTO.getId());
            if (optionalCourse.isPresent()) {
                model.setCourse(optionalCourse.get());
            }
        }

        return model;
    }
}
