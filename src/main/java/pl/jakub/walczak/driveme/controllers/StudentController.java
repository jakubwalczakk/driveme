package pl.jakub.walczak.driveme.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.Student;
import pl.jakub.walczak.driveme.repos.StudentRepository;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public @ResponseBody
    ResponseEntity addNewStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String
            phoneNumber, @RequestParam String email) {

        Student student = new Student(firstName, lastName, phoneNumber, email);
        studentRepository.save(student);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

}
