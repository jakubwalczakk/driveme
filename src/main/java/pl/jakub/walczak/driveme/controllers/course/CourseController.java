package pl.jakub.walczak.driveme.controllers.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.course.CourseDTO;
import pl.jakub.walczak.driveme.model.course.Course;
import pl.jakub.walczak.driveme.services.course.CourseService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody CourseDTO courseDTO) {
        try {
            return ResponseEntity.ok(courseService.addCourse(courseDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCourse(@PathVariable("id") Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(courseService.getCourse(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll() {
        try {
            return ResponseEntity.ok(courseService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
