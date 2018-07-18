package pl.jakub.walczak.driveme.repos.reservation;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.reservation.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
