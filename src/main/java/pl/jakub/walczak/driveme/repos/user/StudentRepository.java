package pl.jakub.walczak.driveme.repos.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.user.Student;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByName(String name);
}
