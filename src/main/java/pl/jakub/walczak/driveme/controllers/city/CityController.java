package pl.jakub.walczak.driveme.controllers.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.city.CityService;

@RestController
@RequestMapping(path = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

}
