package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.user.UserService;

import java.util.Optional;

@Component
public class PracticalExamMapper {

    private UserService userService;
    private CarService carService;
//    private StudentService studentService;
//    private InstructorService instructorService;

    @Autowired
    public PracticalExamMapper(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    public PracticalExamDTO mapModelToDTO(PracticalExam model, PracticalExamDTO dto) {

        dto.setId(model.getId());
        dto.setDateOfExam(model.getDateOfExam());
        dto.setActive(model.getActive());
        dto.setPassed(model.getPassed());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setCar(carService.mapModelToBasicDTO(model.getCar(), CarBasicDTO.builder().build()));
        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        dto.setDurationTime(model.getDurationTime());
        return dto;
    }

    public PracticalExam mapDTOToModel(PracticalExamDTO dto, PracticalExam model) {

        model.setId(dto.getId());
        model.setDateOfExam(dto.getDateOfExam());
        model.setActive(dto.getActive());
        model.setPassed(dto.getPassed());

        CarBasicDTO carDTO = dto.getCar();
        if (carDTO != null) {
            Optional<Car> carOptional = carService.findById(carDTO.getId());
            if (carOptional.isPresent()) {
                model.setCar(carOptional.get());
            }
        }

        UserBasicDTO studentDTO = dto.getStudent();
        if (studentDTO != null) {
            Optional<User> optionalStudent = userService.findById(studentDTO.getId());
            if (optionalStudent.isPresent()) {
                Student student = (Student) optionalStudent.get();
                model.setStudent(student);
            }
        }

        UserBasicDTO instructorDTO = dto.getInstructor();
        if (instructorDTO != null) {
            Optional<User> optionalInstructor = userService.findById(instructorDTO.getId());
            if (optionalInstructor.isPresent()) {
                Instructor instructor = (Instructor) optionalInstructor.get();
                model.setInstructor(instructor);
            }
        }

        return model;
    }
}
