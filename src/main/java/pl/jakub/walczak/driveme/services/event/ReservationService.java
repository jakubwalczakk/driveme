package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.repos.event.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }
}
