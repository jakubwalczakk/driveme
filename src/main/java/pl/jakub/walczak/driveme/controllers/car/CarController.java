package pl.jakub.walczak.driveme.controllers.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.gastype.GasType;
import pl.jakub.walczak.driveme.services.car.CarService;

@Controller
@RequestMapping(path = "/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity saveNewCar() {

        GasType oil = GasType.OIL;
        Car car = new Car("TOYOTA", "KOL M500", oil);

        carService.save(car);

        return new ResponseEntity(car, HttpStatus.OK);
    }
}
