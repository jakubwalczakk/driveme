package pl.jakub.walczak.driveme.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.repos.car.CarRepository;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
