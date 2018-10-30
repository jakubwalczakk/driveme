package pl.jakub.walczak.driveme.controllers.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.services.car.CarService;

import java.util.List;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody CarDTO carDTO) {
        try {
            return ResponseEntity.ok(carService.addCar(carDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id) {
        try {
            carService.deleteCar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(carService.getCar(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/active")
    public ResponseEntity<List<CarDTO>> getActiveCars() {
        try {
            return ResponseEntity.ok(carService.getActiveCars());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll() {
        try {
            return ResponseEntity.ok(carService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
