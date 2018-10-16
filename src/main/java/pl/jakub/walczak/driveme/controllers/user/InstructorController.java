package pl.jakub.walczak.driveme.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.user.InstructorDTO;
import pl.jakub.walczak.driveme.dto.user.StudentDTO;
import pl.jakub.walczak.driveme.dto.user.UserDTO;
import pl.jakub.walczak.driveme.services.user.InstructorService;

import java.util.List;

@RestController
@RequestMapping(path = "/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteInstructor(@PathVariable("id") Long id){
        try{
            instructorService.deleteInstructor(id);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<InstructorDTO> getInstructor(@PathVariable("id") Long id){
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
