package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.services.exam.TheoreticalExamService;

@RestController
@RequestMapping(path = "/theoretical_exam")
public class TheoreticalExamController {

    @Autowired
    private TheoreticalExamService theoreticalExamService;
}
