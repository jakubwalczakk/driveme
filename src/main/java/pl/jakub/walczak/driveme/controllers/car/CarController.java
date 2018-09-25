package pl.jakub.walczak.driveme.controllers.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jakub.walczak.driveme.services.car.CarService;

@Controller
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    private CarService carService;
}
