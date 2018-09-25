package pl.jakub.walczak.driveme.repos.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.user.Instructor;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
