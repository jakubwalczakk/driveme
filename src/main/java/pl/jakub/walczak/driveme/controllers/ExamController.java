package pl.jakub.walczak.driveme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakub.walczak.driveme.model.Exam;
import pl.jakub.walczak.driveme.model.Student;
import pl.jakub.walczak.driveme.model.TheoreticalExam;
import pl.jakub.walczak.driveme.repos.ExamRepository;
import pl.jakub.walczak.driveme.repos.StudentRepository;

import java.util.Date;

@Controller
@RequestMapping(path = "/exams")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/save")
    public @ResponseBody
    String saveExam() {

        Student student = studentRepository
                .findStudentByFirstName("Jakub").get();

        Exam exam = new TheoreticalExam(new Date(), student, 54);

        examRepository.save(exam);

        return "Exam saved";
    }
}
