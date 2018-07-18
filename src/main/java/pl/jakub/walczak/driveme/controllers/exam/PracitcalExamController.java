package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jakub.walczak.driveme.services.exam.PracticalExamService;

@Controller
public class PracitcalExamController {

    @Autowired
    private PracticalExamService practicalExamService;
}
