package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.services.event.ReservationService;

import java.util.List;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> addReservation (@RequestBody ReservationDTO reservationDTO) {
        try {
            return ResponseEntity.ok(reservationService.addReservation(reservationDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteReservation(@PathVariable("id") Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(reservationService.getReservation(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAll() {
        try {
            return ResponseEntity.ok(reservationService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
