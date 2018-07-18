package pl.jakub.walczak.driveme.controllers.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.city.City;
import pl.jakub.walczak.driveme.services.city.CityService;

@Controller
@RequestMapping(path = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addNewCity(@RequestParam String name){

        City city = new City(name);
        cityService.save(city);
        return new ResponseEntity<>(city,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<City> getAllCities() {
        return cityService.findAll();
    }
}
