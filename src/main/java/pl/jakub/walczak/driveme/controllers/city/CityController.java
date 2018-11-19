package pl.jakub.walczak.driveme.controllers.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.services.city.CityService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/city")
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<DrivingCity> addDrivingCity(@RequestBody DrivingCityDTO drivingCityDTO) {
        try {
            return ResponseEntity.ok(cityService.addDrivingCity(drivingCityDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteDrivingCity(@PathVariable("id") Long id) {
        try {
            cityService.deleteDrivingCity(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DrivingCityDTO> getDrivingCity(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(cityService.getDrivingCity(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/active")
    public ResponseEntity<List<DrivingCityDTO>> getActiveCities() {
        try {
            return ResponseEntity.ok(cityService.getActiveCities());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DrivingCityDTO>> getAll() {
        try {
            return ResponseEntity.ok(cityService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
