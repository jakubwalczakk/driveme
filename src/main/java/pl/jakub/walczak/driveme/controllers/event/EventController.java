package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.calendar.CalendarEventsDTO;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.EventDTO;
import pl.jakub.walczak.driveme.model.event.Event;
import pl.jakub.walczak.driveme.services.event.EventService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody EventDTO eventDTO) {
        try {
            return ResponseEntity.ok(eventService.addEvent(eventDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteEvent(@PathVariable("id") Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(eventService.getEvent(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/booking")
    public ResponseEntity<CalendarEventsDTO> getAllSpecifiedEvents(@RequestParam("instructor") String instructor,
                                                                   @RequestParam("brand") String carBrand) {
        try {
            return ResponseEntity.ok(eventService.getAllSpecifiedEvents(instructor, carBrand));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PostMapping(path="/events")
//    public ResponseEntity<List<EventDTO>> getEventsByInstructorAndCar(@RequestBody EventsInfoDTO request){
//        try{
//            return ResponseEntity.ok(eventService.getEventsByInstructorAndCar(request));
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll() {
        try {
            return ResponseEntity.ok(eventService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
