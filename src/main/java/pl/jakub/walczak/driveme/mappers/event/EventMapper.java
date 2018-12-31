package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.EventDTO;
import pl.jakub.walczak.driveme.dto.user.UserBasicDTO;
import pl.jakub.walczak.driveme.model.event.Event;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.services.user.UserService;
import pl.jakub.walczak.driveme.utils.DateFormatter;

@Component
public class EventMapper {

    private UserService userService;

    @Autowired
    public EventMapper(UserService userService) {
        this.userService = userService;
    }

    public EventDTO mapModelToDTO(Event model, EventDTO dto) {
        dto.setId(model.getId());
        dto.setStudent(userService.mapUserBasicModelToDTO(model.getStudent(), UserBasicDTO.builder().build()));
        dto.setStartDate(DateFormatter.formatDateToString(model.getStartDate()));
        dto.setDuration(model.getDuration());
        dto.setFinishDate(DateFormatter.formatDateToString(model.getFinishDate()));
        return dto;
    }

    public Event mapDTOToModel(EventDTO dto, Event model) {
        model.setId(dto.getId());
        model.setStartDate(DateFormatter.parseStringToInstant(dto.getStartDate()));
        model.setDuration(dto.getDuration());
        model.setFinishDate(DateFormatter.parseStringToInstant(dto.getFinishDate()));

        User student = userService.mapUserBasicDTOToModel(dto.getStudent());
        if (student instanceof Student) {
            model.setStudent((Student) student);
        }
        return model;
    }
}
