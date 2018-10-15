package pl.jakub.walczak.driveme.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "instructors")
public class Instructor extends User {

    private Integer availableHours;
    private Integer takenHours;
    @OneToMany(mappedBy = "instructor", orphanRemoval = true)
    private Set<PracticalExam> practicalExams;
    @OneToMany(mappedBy = "instructor", orphanRemoval = true)
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "instructor", orphanRemoval = true)
    private Set<Driving> drivings;

}
