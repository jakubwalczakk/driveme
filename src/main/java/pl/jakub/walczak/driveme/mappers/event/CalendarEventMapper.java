package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.CalendarEventDTO;
import pl.jakub.walczak.driveme.model.event.CalendarEvent;

@Component
public class CalendarEventMapper {

    public CalendarEventDTO mapModelToDTO(CalendarEvent model, CalendarEventDTO dto) {
        return dto;
    }

    public CalendarEvent mapDTOToModel(CalendarEventDTO dto, CalendarEvent model) {
        return model;
    }
}
