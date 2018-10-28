package pl.jakub.walczak.driveme.mappers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.car.CarService;
import pl.jakub.walczak.driveme.services.user.UserService;

@Component
public class PracticalExamMapper {

    private UserService userService;
    private CarService carService;

    @Autowired
    public PracticalExamMapper(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    public PracticalExamDTO mapModelToDTO(PracticalExam model, PracticalExamDTO dto) {

        dto.setId(model.getId());
        dto.setDateOfExam(model.getDateOfExam());
        dto.setActive(model.getActive());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setCar(carService.mapModelToDTO(model.getCar(), CarDTO.builder().build()));
        dto.setInstructor(userService.mapUserBasicModelToDTO(model.getInstructor(), UserBasicDTO.builder().build()));
        dto.setDurationTime(model.getDurationTime());
        return dto;
    }

    public PracticalExam mapDTOToModel(PracticalExamDTO dto, PracticalExam model) {

        model.setId(dto.getId());
        model.setDateOfExam(dto.getDateOfExam());
        model.setActive(dto.getActive());
        model.setCar(carService.mapDTOToModel(dto.getCar(), model.getCar()));
        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student)
            model.setStudent((Student) student);
        User instructor = userService.mapUserBasicDTOToModel(dto.getInstructor());
        if (instructor instanceof Instructor) {
            model.setInstructor((Instructor) instructor);
        }
        return model;
    }
}
