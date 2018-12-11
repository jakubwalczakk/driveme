package pl.jakub.walczak.driveme.repos.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.event.Driving;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface DrivingRepository extends JpaRepository<Driving, Long> {
    List<Driving> findAllByInstructorIdOrderByFinishDateDesc(Long instructorId);
    List<Driving> findAllByStudentIdOrderByFinishDate(Long studentId);
}
