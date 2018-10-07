package pl.jakub.walczak.driveme.services.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private DrivingCityRepository drivingCityRepository;

    public DrivingCity save(DrivingCity drivingCity) {
        return drivingCityRepository.save(drivingCity);
    }

    public List<DrivingCity> findAll() {
        return drivingCityRepository.findAll();
    }
}
