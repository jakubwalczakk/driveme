package pl.jakub.walczak.driveme.controllers.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakub.walczak.driveme.dto.mainpage.MainPageDTO;
import pl.jakub.walczak.driveme.services.mainpage.MainPageService;

@CrossOrigin
@RestController
@RequestMapping("/")
public class MainPageController {

    @Autowired
    public MainPageService mainPageService;

    @GetMapping
    public ResponseEntity<MainPageDTO> getMainPage() {
        return ResponseEntity.ok(mainPageService.getMainPage());
    }
}
