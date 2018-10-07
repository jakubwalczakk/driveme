package pl.jakub.walczak.driveme.controllers.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.car.CarService;

@RestController
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    private CarService carService;
}
