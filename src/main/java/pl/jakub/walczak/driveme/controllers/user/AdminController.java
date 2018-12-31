package pl.jakub.walczak.driveme.controllers.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakub.walczak.driveme.dto.user.AdminRegistrationDTO;
import pl.jakub.walczak.driveme.dto.user.AdminDTO;
import pl.jakub.walczak.driveme.model.user.Admin;
import pl.jakub.walczak.driveme.services.user.AdminService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin")
@Slf4j
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminRegistrationDTO adminRegistrationDTO) {
        try {
            return ResponseEntity.ok(adminService.createAdmin(adminRegistrationDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAdmin(@PathVariable("id") Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(adminService.getAdmin(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAll() {
        try {
            return ResponseEntity.ok(adminService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
