package pl.jakub.walczak.driveme.mappers.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.services.exam.PracticalExamService;
import pl.jakub.walczak.driveme.services.exam.TheoreticalExamService;
import pl.jakub.walczak.driveme.services.user.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private PracticalExamService practicalExamService;
    private TheoreticalExamService theoreticalExamService;
    private UserService userService;

    @Autowired
    public CourseMapper(PracticalExamService practicalExamService, TheoreticalExamService theoreticalExamService, UserService userService) {
        this.practicalExamService = practicalExamService;
        this.theoreticalExamService = theoreticalExamService;
        this.userService = userService;
    }

    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        dto.setId(model.getId());
        dto.setStartDate(model.getStartDate());
        dto.setTakenDrivingHours(model.getTakenDrivingHours());
//        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setPracticalExam(practicalExamService.mapModelToDTO(model.getPracticalExam(), PracticalExamDTO.builder().build()));
        dto.setTheoreticalExams(model.getTheoreticalExams().stream()
                .map(exam -> theoreticalExamService.mapModelToDTO(exam, TheoreticalExamDTO.builder().build()))
                .collect(Collectors.toSet()));
        dto.setStatus(model.getStatus().toString());
        return dto;
    }

    public Course mapDTOToModel(CourseDTO dto, Course model) {
        model.setId(dto.getId());
        model.setStartDate(dto.getStartDate());
        model.setTakenDrivingHours(dto.getTakenDrivingHours());
//        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
//        if (student instanceof Student) {
//            model.setStudent((Student) student);
//        }
        model.setPracticalExam(practicalExamService.mapDTOToModel(dto.getPracticalExam(), model.getPracticalExam()));
        Set<Long> theoreticalExamsToAdd =
                dto.getTheoreticalExams().stream().map(exam -> exam.getId()).collect(Collectors.toSet());
        model.setTheoreticalExams(theoreticalExamService.findAllById(theoreticalExamsToAdd));
        try {
            model.setStatus(CourseStatus.valueOf(dto.getStatus().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setStatus(CourseStatus.DEFAULT);
        }
        return model;
    }
}
