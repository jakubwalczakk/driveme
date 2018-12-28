package pl.jakub.walczak.driveme.controllers.event.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.event.exam.TheoreticalExamDTO;
import pl.jakub.walczak.driveme.model.event.exam.TheoreticalExam;
import pl.jakub.walczak.driveme.services.event.exam.TheoreticalExamService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/theoretical_exam")
public class TheoreticalExamController {

    private TheoreticalExamService theoreticalExamService;

    @Autowired
    public TheoreticalExamController(TheoreticalExamService theoreticalExamService) {
        this.theoreticalExamService = theoreticalExamService;
    }

    @PostMapping
    public ResponseEntity<TheoreticalExam> addTheoreticalExam(@RequestBody TheoreticalExamDTO theoreticalExamDTO) {
        try {
            return ResponseEntity.ok(theoreticalExamService.addTheoreticalExam(theoreticalExamDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTheoreticalExam(@PathVariable("id") Long id) {
        try {
            theoreticalExamService.deleteTheoreticalExam(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TheoreticalExamDTO> getTheoreticalExam(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(theoreticalExamService.getExam(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/student/{id}")
    public ResponseEntity<List<TheoreticalExamDTO>> getTheoreticalExamsOfStudent(@PathVariable("id") Long studentId) {
        try {
            return ResponseEntity.ok(theoreticalExamService.getTheoreticalExamsOfStudent(studentId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<TheoreticalExamDTO>> getAll() {
        try {
            return ResponseEntity.ok(theoreticalExamService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}