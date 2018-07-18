package pl.jakub.walczak.driveme.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.repos.car.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
}
