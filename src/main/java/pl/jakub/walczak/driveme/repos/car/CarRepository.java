package pl.jakub.walczak.driveme.repos.car;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.car.Car;

public interface CarRepository extends CrudRepository<Car,Long> {
}
