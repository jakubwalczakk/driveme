package pl.jakub.walczak.driveme.repos.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.user.Instructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByInstructorIdOrderByFinishDateDesc(Long instructorId);
    List<Reservation> findAllByStudentIdOrderByFinishDateDesc(Long studentId);
}
