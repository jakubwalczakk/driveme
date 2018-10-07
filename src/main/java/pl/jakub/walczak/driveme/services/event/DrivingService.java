package pl.jakub.walczak.driveme.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.repos.event.DrivingRepository;

import java.util.List;

@Service
public class DrivingService {

    @Autowired
    private DrivingRepository drivingRepository;

    public List<Driving> findAll() {
        return drivingRepository.findAll();
    }
}
