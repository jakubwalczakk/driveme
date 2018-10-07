package pl.jakub.walczak.driveme.model.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.model.event.Reservation;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    @Column(name = "brand", nullable = false)
    private CarBrand brand;
    @Column(name="model", nullable = false)
    private String model;
    @Column(name = "license_plate", nullable = false)
    private String licensePlate;
    @Enumerated
    @Column(name = "gas_type", nullable = false)
    private GasType gasType;
    @OneToMany(mappedBy = "car")
    private Set<PracticalExam> practicalExams;
    @OneToMany(mappedBy = "car")
    private Set<Reservation>reservations;
//    private String carPhoto;
}
