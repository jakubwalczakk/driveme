package pl.jakub.walczak.driveme.mappers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.AdminRegistrationDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorRegistrationDTO;
import pl.jakub.walczak.driveme.dto.user.RegistrationDTO;
import pl.jakub.walczak.driveme.dto.user.StudentRegistrationDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.user.Admin;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.address.AddressService;

import java.time.Instant;

@Component
@Slf4j
public class RegistrationMapper {

    private AddressService addressService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationMapper(AddressService addressService, PasswordEncoder passwordEncoder) {
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegistrationDTOToUser(RegistrationDTO dto) {
        try {
            User user;
            UserRole userRole = UserRole.of(dto.getUserRole());
            if (userRole.getValue().equals(UserRole.ADMIN.getValue())) {
                log.info("REJESTRACJA NOWEGO ADMINA");
                user = Admin.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .email(dto.getEmail())
                        .phoneNumber(dto.getPhoneNumber())
                        .userRole(userRole)
                        .active(true)
                        .build();

            } else if (userRole.getValue().equals(UserRole.INSTRUCTOR.getValue())) {
                log.info("REJESTRACJA NOWEGO INSTRUKTORA");
                user = Instructor.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .email(dto.getEmail())
                        .phoneNumber(dto.getPhoneNumber())
                        .userRole(userRole)
                        .active(true)
                        .build();

            } else {
                log.info("REJESTRACJA NOWEGO STUDENTA");
                user = Student.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .pesel(dto.getPesel())
                        .email(dto.getEmail())
                        .phoneNumber(dto.getPhoneNumber())
                        .userRole(userRole)
                        .address(addressService.addAddress(dto.getAddress()))
                        .course(new Course())
                        .registrationDate(Instant.now())
                        .active(true)
                        .build();
            }
            return user;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Cannot find a right value for UserRole enum.");
        }
    }

    public Admin mapRegistrationDTOToInstructor(AdminRegistrationDTO dto) {
        Admin model = Admin.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .userRole(UserRole.ADMIN)
                .build();

        return model;
    }

    public Instructor mapRegistrationDTOToInstructor(InstructorRegistrationDTO dto) {
        Instructor model = Instructor.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .userRole(UserRole.INSTRUCTOR)
                .build();

        return model;
    }

    public Student mapRegistrationDTOToStudent(StudentRegistrationDTO dto) {
        Student model = Student.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .pesel(dto.getPesel())
                .address(addressService.mapDTOToModel(dto.getAddress(), Address.builder().build()))
                .userRole(UserRole.STUDENT)
                .registrationDate(Instant.now())
                .active(false)
                .build();

        return model;
    }
}
