package pl.jakub.walczak.driveme.services.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.city.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public DrivingCity save(DrivingCity drivingCity) {
        return cityRepository.save(drivingCity);
    }

    public Iterable<DrivingCity> findAll() {
        return cityRepository.findAll();
    }
}
