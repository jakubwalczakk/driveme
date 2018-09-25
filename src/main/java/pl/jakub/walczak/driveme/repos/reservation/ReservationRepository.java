package pl.jakub.walczak.driveme.repos.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.reservation.Reservation;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
