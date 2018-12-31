package pl.jakub.walczak.driveme.mappers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    public StudentMapper(AddressService addressService, ReservationService reservationService, DrivingService drivingService, CourseService courseService, PasswordEncoder passwordEncoder) {
        this.addressService = addressService;
        this.reservationService = reservationService;
        this.drivingService = drivingService;
        this.courseService = courseService;
        this.passwordEncoder = passwordEncoder;
    }

    // -- mappers methods --
    public StudentDTO mapModelToDTO(Student model, StudentDTO dto) {
        log.info("Mapping Student model to StudentDTO");
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setUserRole(model.getUserRole().getValue());
        dto.setActive(model.getActive());
        dto.setPesel(model.getPesel());
        dto.setRegistrationDate(model.getRegistrationDate());
        dto.setAddress(addressService.mapModelToDTO(model.getAddress(), AddressDTO.builder().build()));
        dto.setCourse(courseService.mapModelToDTO(model.getCourse(), CourseDTO.builder().build()));
        return dto;
    }

    public Student mapDTOToModel(StudentDTO dto, Student model) {
        log.info("Mapping StudentDTO to Student model");
        model.setId(dto.getId() == null ? model.getId() : dto.getId());
        model.setName(dto.getName() == null ? model.getName() : dto.getName());
        model.setSurname(dto.getSurname() == null ? model.getSurname() : dto.getSurname());
        model.setEmail(dto.getEmail() == null ? model.getEmail() : dto.getEmail());
        model.setPassword(dto.getPassword() == null ? model.getPassword() : passwordEncoder.encode(dto.getPassword()));
        model.setPhoneNumber(dto.getPhoneNumber() == null ? model.getPhoneNumber() : dto.getPhoneNumber());
        try {
            model.setUserRole(dto.getUserRole() == null ? model.getUserRole() :
                    UserRole.of(dto.getUserRole()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setUserRole(UserRole.DEFAULT);
        }
        model.setActive(dto.getActive() == null ? model.getActive() : dto.getActive());
        model.setPesel(dto.getPesel() == null ? model.getPesel() : dto.getPesel());
        model.setRegistrationDate(dto.getRegistrationDate() == null ? model.getRegistrationDate() : dto.getRegistrationDate());

        AddressDTO addressDTO = dto.getAddress();
        if (addressDTO != null) {
            Optional<Address> optionalAddress = addressService.findByCityAndZipCodeAndStreetAndHouseNo(addressDTO.getCity(),
                    addressDTO.getZipCode(), addressDTO.getStreet(), addressDTO.getHouseNo());
            if (optionalAddress.isPresent()) {
                model.setAddress(optionalAddress.get());
            } else {
                Address address = addressService.addAddress(addressDTO);
                model.setAddress(address);
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
