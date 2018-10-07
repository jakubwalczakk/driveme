package pl.jakub.walczak.driveme.mappers.event;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.event.ReservationDTO;
import pl.jakub.walczak.driveme.model.event.Reservation;

@Component
public class ReservationMapper {

    public ReservationDTO mapModelToDTO(Reservation model, ReservationDTO dto) {
        return dto;
    }

    public Reservation mapDTOToModel(ReservationDTO dto, Reservation model) {
        return model;
    }
}
