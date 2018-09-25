package pl.jakub.walczak.driveme.controllers.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jakub.walczak.driveme.services.city.CityService;

@Controller
@RequestMapping(path = "/cities")
public class CityController {

    @Autowired
    private CityService cityService;

}
