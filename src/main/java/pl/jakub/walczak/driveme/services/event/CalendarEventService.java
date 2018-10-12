package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.mappers.event.CalendarEventMapper;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.repos.event.CalendarEventRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarEventService {

    private CalendarEventRepository calendarEventRepository;
    private CalendarEventMapper calendarEventMapper;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository, CalendarEventMapper calendarEventMapper) {
        this.calendarEventRepository = calendarEventRepository;
        this.calendarEventMapper = calendarEventMapper;
    }

    // -- methods for controller --
    public List<CalendarEventDTO> getAll() {
        return findAll().stream().map(event -> mapModelToDTO(event, CalendarEventDTO.builder().build())).collect(Collectors.toList());
    }

    // -- dao methods --
    public List<CalendarEvent> findAll() {
        return calendarEventRepository.findAll();
    }

    // -- mapper methods --
    public CalendarEventDTO mapModelToDTO(CalendarEvent model, CalendarEventDTO dto) {
        return calendarEventMapper.mapModelToDTO(model, dto);
    }

    public CalendarEvent mapDTOToModel(CalendarEventDTO dto, CalendarEvent model) {
        if (dto.getId() != null) {
            Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findById(dto.getId());
            if (optionalCalendarEvent.isPresent()) {
                model = optionalCalendarEvent.get();
            }
        }
        model = calendarEventMapper.mapDTOToModel(dto, model);
        return calendarEventRepository.save(model);
    }
}
