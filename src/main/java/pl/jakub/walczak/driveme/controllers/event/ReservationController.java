package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.services.event.ReservationService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            return ResponseEntity.ok(reservationService.addReservation(reservationDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/accept/{id}")
    public ResponseEntity<Boolean> acceptReservation(@PathVariable("id") Long reservationId) {
        try {
            return ResponseEntity.ok(reservationService.acceptReservation(reservationId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/deny/{id}")
    public ResponseEntity<Boolean> denyReservation(@PathVariable("id") Long reservationId) {
        try {
            return ResponseEntity.ok(reservationService.denyReservation(reservationId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
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


    @GetMapping(path = "/instructor")
    public ResponseEntity<List<ReservationDTO>> getReservationsByInstructor() {
        try {
            return ResponseEntity.ok(reservationService.getReservationsByInstructor());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/student")
    public ResponseEntity<List<ReservationDTO>> getReservationsByStudent() {
        try {
            return ResponseEntity.ok(reservationService.getReservationsByStudent());
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
