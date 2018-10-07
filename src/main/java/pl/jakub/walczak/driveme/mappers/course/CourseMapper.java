package pl.jakub.walczak.driveme.mappers.course;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.model.course.Course;

@Component
public class CourseMapper {

    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        return dto;
    }

    public Course mapDTOToModel(CourseDTO dto, Course model) {
        return model;
    }
}
