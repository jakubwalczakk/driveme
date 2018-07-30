package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.model.address.Address;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.model.exam.PracticalExam;
import pl.jakub.walczak.driveme.model.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.model.gastype.GasType;
import pl.jakub.walczak.driveme.model.payment.Payment;
import pl.jakub.walczak.driveme.model.reservation.Reservation;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.repos.user.InstructorRepository;
import pl.jakub.walczak.driveme.services.course.CourseService;
import pl.jakub.walczak.driveme.services.reservation.ReservationService;
import pl.jakub.walczak.driveme.services.user.StudentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private ReservationService reservationService;

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
        Student student = new Student("imię", "nazwisko", "111111111", "email@o.pl", "password", "pesel", address,
                new Date());

        Instructor instructor = new Instructor("instrktor", "fajny", "1111", "ins@o2.pl", "passssss", 12, 18);

        List<Payment> payments = new ArrayList<>();
        Payment p1 = new Payment(15.0, new Date());
        Payment p2 = new Payment(22.5, new Date());
        payments.add(p1);
        payments.add(p2);
        student.setPayments(payments);

        Car car = new Car("OPEL", "XYZ1111", GasType.OIL);

        PracticalExam practicalExam = new PracticalExam(new Date(), car, instructor);
        List<TheoreticalExam> tExams = new ArrayList<>();
        tExams.add(new TheoreticalExam(new Date(), 24));
        tExams.add(new TheoreticalExam(new Date(), 32));
        Course course = new Course(new Date(), 15, 15, 15, tExams, practicalExam);
        student.setCourse(course);


        Reservation reservation = new Reservation(student,instructor,car,new Date(),150,new DrivingCity("BYTOM"));

        reservationService.save(reservation);
        //studentService.save(student);
        //instructorRepository.save(instructor);

        return new ResponseEntity(student, HttpStatus.OK);
    }

}
