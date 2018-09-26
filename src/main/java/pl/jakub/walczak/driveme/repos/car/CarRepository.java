package pl.jakub.walczak.driveme.repos.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.model.car.Car;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
}
