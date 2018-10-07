package pl.jakub.walczak.driveme.services.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.repos.course.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
