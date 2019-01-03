package pl.jakub.walczak.driveme.services.course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.enums.CourseStatus;
import pl.jakub.walczak.driveme.mappers.course.CourseMapper;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.course.CourseRepository;
import pl.jakub.walczak.driveme.utils.AuthenticationUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, AuthenticationUtil authenticationUtil) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.authenticationUtil = authenticationUtil;
    }

    // -- methods for controller --
    public Course addCourse(CourseDTO courseDTO) {
        log.info("Adding new Course...");
        Course course = mapDTOToModel(courseDTO, Course.builder().build());
        course.setStatus(CourseStatus.IN_PROGRESS);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        log.info("Deleting the Course with id = " + id);
        Optional<Course> courseToDelete = courseRepository.findById(id);
        courseRepository.delete(courseToDelete.orElseThrow(() ->
                new NoSuchElementException("Cannot DELETE Course with given id = " + id)));
    }

    public CourseDTO getCourse(Long id) {
        log.info("Getting the Course with id = " + id);
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return mapModelToDTO(optionalCourse.orElseThrow(() ->
                new NoSuchElementException("Cannot GET Course with given id = " + id)), CourseDTO.builder().build());
    }

    public CourseDTO getCourseByStudent() {
        log.info("Getting the Course of current logged Student");
        User currentLoggedUser = authenticationUtil.getCurrentLoggedUser();
        if (currentLoggedUser instanceof Student) {
            Course course = ((Student) currentLoggedUser).getCourse();
            return courseMapper.mapModelToDTO(course, CourseDTO.builder().build());
        }
        throw new SecurityException("Current logged user is not an instance of Student");
    }

    public List<CourseDTO> getAll() {
        log.info("Getting all Courses");
        return findAll().stream().map(course -> mapModelToDTO(course, CourseDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    // -- mapper methods --
    public CourseDTO mapModelToDTO(Course model, CourseDTO dto) {
        return model == null ? null : courseMapper.mapModelToDTO(model, dto);
    }

    public Course mapDTOToModel(CourseDTO dto, Course model) {
        if (dto.getId() != null) {
            Optional<Course> optionalCourse = courseRepository.findById(dto.getId());
            model = optionalCourse.orElse(model);
        }
        model = courseMapper.mapDTOToModel(dto, model);
        return courseRepository.save(model);
    }
}
