package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;
import pl.jakub.walczak.driveme.services.user.StudentService;

import java.util.Date;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorRepository instructorRepository;

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

    @PostMapping("/course")
    public @ResponseBody
    ResponseEntity addNewCourse() {

        Address address = new Address("Miasto", "11-111", "ulica", 2, 1);
        Student student = new Student("imię", "nazwisko", "111111111", "email@o.pl", "password", "pesel", address, new Date());

        Instructor instructor = new Instructor("instrktor", "fajny", "1111", "ins@o2.pl", "passssss", 12, 18);


        //        student.setRegistrationDate(new Date());
        //                List<Payment> payments = new ArrayList<>();
        //                Payment p1 = new Payment(student, 15.0, new Date());
        //                Payment p2 = new Payment(student, 22.5, new Date());
        //                payments.add(p1);
        //                payments.add(p2);
        //                student.setPayments(payments);
        //
        //        PracticalExam practicalExam = new PracticalExam(new Date(), null, null, null);
        //        Course course = new Course(new Date(), 15, 15, 15, null, practicalExam);
        //        student.setCourse(course);

        studentService.save(student);
        instructorRepository.save(instructor);

        return new ResponseEntity(student, HttpStatus.OK);
    }

}
