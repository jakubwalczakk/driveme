package pl.jakub.walczak.driveme.controllers.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.services.city.CityService;

@Controller
@RequestMapping(path = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addNewCity(@RequestParam String name){

        DrivingCity drivingCity = new DrivingCity(name);
        cityService.save(drivingCity);
        return new ResponseEntity<>(drivingCity,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<DrivingCity> getAllCities() {
        return cityService.findAll();
    }
}
