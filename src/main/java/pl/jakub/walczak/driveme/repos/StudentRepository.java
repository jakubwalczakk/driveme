package pl.jakub.walczak.driveme.repos;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.Student;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentByFirstName(String firstName);
}
