package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.event.DrivingService;

@RestController
@RequestMapping(path = "/driving")
public class DrivingController {

    @Autowired
    private DrivingService drivingService;
}
