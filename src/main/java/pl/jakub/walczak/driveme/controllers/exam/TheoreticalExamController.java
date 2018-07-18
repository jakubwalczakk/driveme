package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.jakub.walczak.driveme.services.exam.TheoreticalExamService;

@Controller
public class TheoreticalExamController {

    @Autowired
    private TheoreticalExamService theoreticalExamService;
}
