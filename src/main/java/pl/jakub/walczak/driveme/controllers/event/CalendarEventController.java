package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.services.event.CalendarEventService;

import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class CalendarEventController {

    private CalendarEventService calendarEventService;

    @Autowired
    public CalendarEventController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    @PostMapping
    public ResponseEntity<CalendarEvent> addCalendarEvent(@RequestBody CalendarEventDTO calendarEventDTO) {
        try {
            return ResponseEntity.ok(calendarEventService.addCalendarEvent(calendarEventDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCalendarEvent(@PathVariable("id") Long id) {
        try {
            calendarEventService.deleteCalendarEvent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CalendarEventDTO> getCalendarEvent(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(calendarEventService.getCalendarEvent(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CalendarEventDTO>> getAll() {
        try {
            return ResponseEntity.ok(calendarEventService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
