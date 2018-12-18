package pl.jakub.walczak.driveme.repos.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.user.Instructor;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findByEmail(String email);
}
