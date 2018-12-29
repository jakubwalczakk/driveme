package pl.jakub.walczak.driveme.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.event.DrivingDTO;
import pl.jakub.walczak.driveme.dto.event.RateDrivingDTO;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.services.event.DrivingService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/driving")
public class DrivingController {

    private DrivingService drivingService;

    @Autowired
    public DrivingController(DrivingService drivingService) {
        this.drivingService = drivingService;
    }

    @PostMapping
    public ResponseEntity<Driving> addDriving(@RequestBody DrivingDTO drivingDTO) {
        try {
            return ResponseEntity.ok(drivingService.addDriving(drivingDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/rate")
    public ResponseEntity<DrivingDTO> rateDriving(@RequestBody RateDrivingDTO rateDrivingDTO) {
        try {
                return ResponseEntity.ok(drivingService.rateDriving(rateDrivingDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteDriving(@PathVariable("id") Long id) {
        try {
            drivingService.deleteDriving(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DrivingDTO> getDriving(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(drivingService.getDriving(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/instructor")
    public ResponseEntity<List<DrivingDTO>> getDrivingsByInstructor() {
        try {
            return ResponseEntity.ok(drivingService.getDrivingsByInstructor());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/student")
    public ResponseEntity<List<DrivingDTO>> getDrivingsByStudent() {
        try {
            return ResponseEntity.ok(drivingService.getDrivingsByStudent());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<DrivingDTO>> getAll() {
        try {
            return ResponseEntity.ok(drivingService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
