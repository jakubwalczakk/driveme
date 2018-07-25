package pl.jakub.walczak.driveme.repos.city;

import org.springframework.data.repository.CrudRepository;
import pl.jakub.walczak.driveme.model.city.DrivingCity;

public interface CityRepository extends CrudRepository<DrivingCity, Long> {
}
