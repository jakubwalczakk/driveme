package pl.jakub.walczak.driveme.controllers.event.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.event.exam.PracticalExamDTO;
import pl.jakub.walczak.driveme.model.event.exam.PracticalExam;
import pl.jakub.walczak.driveme.services.event.exam.PracticalExamService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/practical_exam")
public class PracticalExamController {

    private PracticalExamService practicalExamService;

    @Autowired
    public PracticalExamController(PracticalExamService practicalExamService) {
        this.practicalExamService = practicalExamService;
    }

    @PostMapping
    public ResponseEntity<PracticalExam> addPracticalExam(@RequestBody PracticalExamDTO practicalExamDTO) {
        try {
            return ResponseEntity.ok(practicalExamService.addPracticalExam(practicalExamDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletePracticalExam(@PathVariable("id") Long id) {
        try {
            practicalExamService.deletePracticalExam(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PracticalExamDTO> getPracticalExam(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(practicalExamService.getPracticalExam(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/student")
    public ResponseEntity<PracticalExamDTO> getPracticalExamOfStudent() {
        try {
            return ResponseEntity.ok(practicalExamService.getPracticalExamOfStudent());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/instructor")
    public ResponseEntity<List<PracticalExamDTO>> getPracticalExamsOfInstructor() {
        try {
            return ResponseEntity.ok(practicalExamService.getPracticalExamsOfInstructor());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PracticalExamDTO>> getAll() {
        try {
            return ResponseEntity.ok(practicalExamService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
