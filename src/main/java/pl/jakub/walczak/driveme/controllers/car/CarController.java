package pl.jakub.walczak.driveme.controllers.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jakub.walczak.driveme.services.car.CarService;

@Controller
public class CarController {

    @Autowired
    private CarService carService;
}
