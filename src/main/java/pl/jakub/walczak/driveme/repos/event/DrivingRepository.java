package pl.jakub.walczak.driveme.repos.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.event.Driving;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DrivingRepository extends JpaRepository<Driving,Long> {
}