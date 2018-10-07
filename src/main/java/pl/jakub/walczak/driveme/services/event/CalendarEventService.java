package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.repos.event.CalendarEventRepository;

import java.util.List;

@Service
public class CalendarEventService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    public List<CalendarEvent> findAll() {
        return calendarEventRepository.findAll();
    }
}
