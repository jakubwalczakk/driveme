package pl.jakub.walczak.driveme.repos.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findStudentByName(String name);
}
