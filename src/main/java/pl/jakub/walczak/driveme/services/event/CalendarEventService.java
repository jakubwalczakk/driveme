package pl.jakub.walczak.driveme.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.mappers.event.CalendarEventMapper;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;
import pl.jakub.walczak.driveme.repos.event.CalendarEventRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
    public CalendarEvent addCalendarEvent(CalendarEventDTO calendarEventDTO) {
        log.info("Adding new CalendarEvent...");
        CalendarEvent calendarEvent = mapDTOToModel(calendarEventDTO, CalendarEvent.builder().build());
        return calendarEventRepository.save(calendarEvent);
    }

    public void deleteCalendarEvent(Long id) {
        log.info("Deleting the CalendarEvent with id = " + id);
        Optional<CalendarEvent> calendarEventToDelete = calendarEventRepository.findById(id);
        if (calendarEventToDelete.isPresent()) {
            calendarEventRepository.delete(calendarEventToDelete.get());
        } else {
            throw new NoSuchElementException("Cannot DELETE CalendarEvent with given id = " + id);
        }
    }

    public CalendarEventDTO getCalendarEvent(Long id) {
        log.info("Getting the CalendarEvent with id = " + id);
        Optional<CalendarEvent> optionalCalendarEvent = calendarEventRepository.findById(id);
        if (optionalCalendarEvent.isPresent()) {
            return mapModelToDTO(optionalCalendarEvent.get(), CalendarEventDTO.builder().build());
        } else {
            throw new NoSuchElementException("Cannot GET CalendarEvent with given id = " + id);
        }
    }

    public List<CalendarEventDTO> getAll() {
        log.info("Getting all CalendarEvents");
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
