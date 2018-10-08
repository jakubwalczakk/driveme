package pl.jakub.walczak.driveme.controllers.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.dto.exam.ExamDTO;
import pl.jakub.walczak.driveme.services.exam.ExamService;
import pl.jakub.walczak.driveme.services.user.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAll() {
        return ResponseEntity.ok(examService.getAll());
    }
}
