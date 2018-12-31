package pl.jakub.walczak.driveme.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.user.Instructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservations")
public class Reservation extends Event {


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;
    @Enumerated
    private CarBrand carBrand;
    @ManyToOne(fetch = FetchType.LAZY)
    private DrivingCity drivingCity;
    private Boolean accepted;
}
