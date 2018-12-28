package pl.jakub.walczak.driveme.model.event.exam;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.event.Event;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "exams")
public class Exam extends Event {

    @Column(name = "status", nullable = false)
    private Boolean passed;
}
