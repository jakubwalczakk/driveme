package pl.jakub.walczak.driveme.repos.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.city.DrivingCity;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface DrivingCityRepository extends JpaRepository<DrivingCity, Long> {
    Optional<DrivingCity> findByName(String name);

    Set<DrivingCity> findAllByActive(boolean active);
}
