package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakub.walczak.driveme.model.exam.Exam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.services.exam.ExamService;
import pl.jakub.walczak.driveme.services.user.StudentService;

import java.util.Date;

@Controller
@RequestMapping(path = "/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/save")
    public @ResponseBody
    String saveExam() {

        Student student = studentService
                .findStudentByFirstName("Jakub").get();

        Exam exam = new TheoreticalExam(new Date(), 54);

        examService.save(exam);

        return "Exam saved";
    }
}
