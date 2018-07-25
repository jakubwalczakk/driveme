package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.services.user.StudentService;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public @ResponseBody
    ResponseEntity addNewStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String
            phoneNumber, @RequestParam String email, @RequestParam String password, @RequestParam String pesel,
                                 @RequestParam String cityName, @RequestParam
                                         String zipCode, @RequestParam String street, @RequestParam int houseNumber,
                                 @RequestParam int localNumber) {

        Address address = new Address(cityName, zipCode, street, houseNumber, localNumber);
        Student student = new Student(firstName, lastName, phoneNumber, email, password, pesel, address);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Student> getAllStudents() {
        return studentService.findAll();
    }

}
