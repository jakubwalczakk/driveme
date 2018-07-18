package pl.jakub.walczak.driveme.controllers.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jakub.walczak.driveme.services.reservation.ReservationService;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
}
