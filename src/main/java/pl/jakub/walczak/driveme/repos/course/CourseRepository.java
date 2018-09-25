package pl.jakub.walczak.driveme.repos.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.course.Course;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {
}
