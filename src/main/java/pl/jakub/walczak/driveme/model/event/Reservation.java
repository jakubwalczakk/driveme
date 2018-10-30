package pl.jakub.walczak.driveme.model.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "reservations")
public class Reservation extends CalendarEvent {

    private Boolean status;
}
