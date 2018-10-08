package pl.jakub.walczak.driveme.controllers.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.services.car.CarService;

import java.util.List;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll(){
        return ResponseEntity.ok(carService.getAll());
    }
}
