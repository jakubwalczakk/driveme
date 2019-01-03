package pl.jakub.walczak.driveme.mappers.event.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

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
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setDuration(model.getDuration());
        dto.setFinishDate(DateFormatter.formatDateToString(model.getFinishDate()));
        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        dto.setCar(carService.mapModelToBasicDTO(model.getCar(), CarBasicDTO.builder().build()));
        dto.setPassed(model.getPassed());
        dto.setEventType(model.getEventType().getValue());
        return dto;
    }

    public PracticalExam mapDTOToModel(PracticalExamDTO dto, PracticalExam model) {

        model.setId(dto.getId());
        model.setStartDate(dto.getStartDate() == null ? model.getStartDate() : DateFormatter.parseStringToInstant(dto.getStartDate()));
        model.setDuration(dto.getDuration() == null ? model.getDuration() : dto.getDuration());
        model.setFinishDate(dto.getFinishDate() == null ? model.getFinishDate() : DateFormatter.parseStringToInstant(dto.getFinishDate()));

        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }

        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof Instructor) {
            model.setInstructor((Instructor) instructor);
        }

        CarBasicDTO carDTO = dto.getCar();
        if (carDTO != null) {
            Optional<Car> carOptional = carService.findById(carDTO.getId());
            model.setCar(carOptional.orElse(model.getCar()));
        }

        model.setPassed(dto.getPassed() == null ? model.getPassed() : dto.getPassed());
        return model;
    }
}
