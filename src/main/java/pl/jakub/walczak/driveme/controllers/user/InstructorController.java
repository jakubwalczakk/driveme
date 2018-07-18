package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jakub.walczak.driveme.services.user.InstructorService;

@Controller
@RequestMapping(path = "/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;
}
