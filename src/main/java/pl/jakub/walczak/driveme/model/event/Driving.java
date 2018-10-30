package pl.jakub.walczak.driveme.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.enums.Rating;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "drivings")
public class Driving extends CalendarEvent {

    @Enumerated
    private Rating rating;
}

