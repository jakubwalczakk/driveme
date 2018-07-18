package pl.jakub.walczak.driveme.repos.user;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.user.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
