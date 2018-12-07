package pl.jakub.walczak.driveme.model.event;

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

    private String title;
    @Enumerated
    private Rating rating;
    private String comment;
}

