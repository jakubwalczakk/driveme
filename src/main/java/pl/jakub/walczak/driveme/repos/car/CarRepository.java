package pl.jakub.walczak.driveme.repos.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.model.car.Car;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
    Set<Car> findAllByActive(boolean active);
    Set<Car> findAllCarByBrand(CarBrand brand);
    @Query("SELECT DISTINCT brand FROM cars")
    Set<CarBrand> findAllCarBrands();
}
