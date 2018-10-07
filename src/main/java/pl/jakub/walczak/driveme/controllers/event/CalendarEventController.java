package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.event.CalendarEventService;

@RestController
@RequestMapping(path = "/event")
public class CalendarEventController {

    @Autowired
    private CalendarEventService calendarEventService;
}
