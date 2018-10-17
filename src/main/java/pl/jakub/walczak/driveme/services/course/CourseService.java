package pl.jakub.walczak.driveme.services.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.mappers.course.CourseMapper;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.repos.course.CourseRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    // -- methods for controller --
    public Course addCourse(CourseDTO courseDTO) {
        Course course = mapDTOToModel(courseDTO, Course.builder().build());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Optional<Course> courseToDelete = courseRepository.findById(id);
        if (courseToDelete.isPresent()) {
            courseRepository.delete(courseToDelete.get());
        } else {
            throw new NoSuchElementException();
        }
    }

    public CourseDTO getCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            return mapModelToDTO(optionalCourse.get(), CourseDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<CourseDTO> getAll() {
        return findAll().stream().map(course -> mapModelToDTO(course, CourseDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    // -- mapper methods --

    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        return courseMapper.mapModelToDTO(model, dto);
    }

    public Course mapDTOToModel(CourseDTO dto, Course model) {
        if (dto.getId() != null) {
            Optional<Course> optionalCourse = courseRepository.findById(dto.getId());
            if (optionalCourse.isPresent()) {
                model = optionalCourse.get();
            }
        }
        model = courseMapper.mapDTOToModel(dto, model);
        return courseRepository.save(model);
    }
}
