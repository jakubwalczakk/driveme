package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.user.InstructorService;

@RestController
@RequestMapping(path = "/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;
}
