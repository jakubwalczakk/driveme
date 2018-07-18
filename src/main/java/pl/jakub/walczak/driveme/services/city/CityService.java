package pl.jakub.walczak.driveme.services.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.city.City;
import pl.jakub.walczak.driveme.repos.city.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }

    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }
}
