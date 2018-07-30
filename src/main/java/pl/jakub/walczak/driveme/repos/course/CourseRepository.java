package pl.jakub.walczak.driveme.repos.course;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.course.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
