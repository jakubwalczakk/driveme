package pl.jakub.walczak.driveme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jakub.walczak.driveme.model.Student;
import pl.jakub.walczak.driveme.repos.StudentRepository;

@Controller
@RequestMapping(path = "/demo")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/add")
    public @ResponseBody String addNewStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber, @RequestParam String email){

        Student student = new Student(firstName,lastName,phoneNumber,email);
        studentRepository.save(student);
        return "Student saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student>getAllStudents(){
        return studentRepository.findAll();
    }

}
