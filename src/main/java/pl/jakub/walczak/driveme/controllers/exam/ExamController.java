package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jakub.walczak.driveme.services.exam.ExamService;
import pl.jakub.walczak.driveme.services.user.StudentService;

@Controller
@RequestMapping(path = "/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;
}
