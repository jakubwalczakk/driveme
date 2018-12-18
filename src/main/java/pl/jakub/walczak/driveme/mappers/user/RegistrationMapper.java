package pl.jakub.walczak.driveme.mappers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.user.InstructorRegistrationDTO;
import pl.jakub.walczak.driveme.dto.user.StudentRegistrationDTO;
import pl.jakub.walczak.driveme.dto.user.UserRegistrationDTO;
import pl.jakub.walczak.driveme.enums.UserRole;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.address.AddressService;

import java.time.Instant;

@Component
public class RegistrationMapper {

    private AddressService addressService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationMapper(AddressService addressService, PasswordEncoder passwordEncoder) {
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    public User mapRegistrationDTOToUser(UserRegistrationDTO dto) {
        User model = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
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
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .availableHours(dto.getAvailableHours())
                .userRole(UserRole.INSTRUCTOR)
                .build();

        return model;
    }

    public Student mapRegistrationDTOToStudent(StudentRegistrationDTO dto) {
        Student model = Student.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
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
