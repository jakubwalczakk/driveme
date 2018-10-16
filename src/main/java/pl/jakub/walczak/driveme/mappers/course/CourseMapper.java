package pl.jakub.walczak.driveme.mappers.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.dto.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.services.exam.PracticalExamService;
import pl.jakub.walczak.driveme.services.exam.TheoreticalExamService;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private PracticalExamService practicalExamService;
    private TheoreticalExamService theoreticalExamService;

    @Autowired
    public CourseMapper(PracticalExamService practicalExamService, TheoreticalExamService theoreticalExamService) {
        this.practicalExamService = practicalExamService;
        this.theoreticalExamService = theoreticalExamService;
    }

    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        dto.setId(model.getId());
        dto.setStartDate(model.getStartDate());
        dto.setTakenDrivingHours(model.getTakenDrivingHours());
        dto.setPracticalExam(practicalExamService.mapModelToDTO(model.getPracticalExam(), dto.getPracticalExam()));
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
