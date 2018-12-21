package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.dto.user.InstructorRegistrationDTO;
import pl.jakub.walczak.driveme.model.user.Instructor;
import pl.jakub.walczak.driveme.security.ApiResponse;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/instructor")
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody InstructorRegistrationDTO instructorRegistrationDTO) {
        try {
            return ResponseEntity.ok(instructorService.createInstructor(instructorRegistrationDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/activate/{id}")
    public ResponseEntity<Instructor> activateInstructor(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(instructorService.activateInstructor(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateInstructor(@RequestBody InstructorDTO instructorDTO) {
        try {
            return ResponseEntity.ok(instructorService.updateInstructor(instructorDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteInstructor(@PathVariable("id") Long id) {
        try {
            instructorService.deleteInstructor(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(instructorService.getInstructor(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAll() {
        try {
            return ResponseEntity.ok(instructorService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
