package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.model.exam.Exam;
import pl.jakub.walczak.driveme.services.exam.ExamService;

import java.util.List;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {

    private ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody ExamDTO examDTO){
        try{
            return ResponseEntity.ok(examService.addExam(examDTO));
        }catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteExam(@PathVariable("id") Long id){
        try{
            examService.deleteExam(id);
            return ResponseEntity.ok().build();
        }catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExamDTO> getExam(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(examService.getExam(id));
        }catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAll() {
        try {
            return ResponseEntity.ok(examService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
