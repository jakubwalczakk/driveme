package pl.jakub.walczak.driveme.controllers.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.services.city.CityService;

import java.util.List;

@RestController
@RequestMapping(path = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<DrivingCityDTO>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

}
