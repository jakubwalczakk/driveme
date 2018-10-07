package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.model.event.Driving;

@Component
public class DrivingMapper {

    public DrivingDTO mapModelToDTO(Driving model, DrivingDTO dto) {
        return dto;
    }

    public Driving mapDTOToModel(DrivingDTO dto, Driving model) {
        return model;
    }
}
