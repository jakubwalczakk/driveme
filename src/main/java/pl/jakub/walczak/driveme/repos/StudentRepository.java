package pl.jakub.walczak.driveme.repos;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
