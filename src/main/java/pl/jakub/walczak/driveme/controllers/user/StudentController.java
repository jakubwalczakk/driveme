package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.dto.user.StudentRegistrationDTO;
import pl.jakub.walczak.driveme.model.user.Student;
import pl.jakub.walczak.driveme.services.user.StudentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRegistrationDTO studentRegistrationDTO) {
        try {
            return ResponseEntity.ok(studentService.createStudent(studentRegistrationDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody StudentDTO studentDTO) {
        try {
            return ResponseEntity.ok(studentService.updateStudent(studentDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/activate/{id}")
    public ResponseEntity<Student> activateStudent(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(studentService.activateStudent(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(studentService.getStudent(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        try {
            return ResponseEntity.ok(studentService.getAllOrderByRegistrationDate());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}