package pl.jakub.walczak.driveme.repos.city;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.city.DrivingCity;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CityRepository extends CrudRepository<DrivingCity, Long> {
}
