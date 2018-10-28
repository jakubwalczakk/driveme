package pl.jakub.walczak.driveme.services.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.mappers.city.DrivingCityMapper;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.model.event.Driving;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {

    private DrivingCityRepository drivingCityRepository;
    private DrivingCityMapper drivingCityMapper;

    @Autowired
    public CityService(DrivingCityRepository drivingCityRepository, DrivingCityMapper drivingCityMapper) {
        this.drivingCityRepository = drivingCityRepository;
        this.drivingCityMapper = drivingCityMapper;
    }

    // -- methods for controller --
    public DrivingCity addDrivingCity(DrivingCityDTO drivingCityDTO) {
        Optional<DrivingCity> drivingCityOptional = drivingCityRepository.findByName(drivingCityDTO.getName());
        if (drivingCityOptional.isPresent()) {
            DrivingCity drivingCity = drivingCityOptional.get();
            drivingCity.setActive(true);
            return drivingCityRepository.save(drivingCity);
        }
        DrivingCity drivingCity = mapDTOToModel(drivingCityDTO, DrivingCity.builder().build());
        drivingCity.setActive(true);
        return drivingCityRepository.save(drivingCity);
    }

    public void deleteDrivingCity(Long id) {
        Optional<DrivingCity> drivingCityToDelete = drivingCityRepository.findById(id);
        if (drivingCityToDelete.isPresent()) {
            DrivingCity drivingCity = drivingCityToDelete.get();
            drivingCity.setActive(false);
            drivingCityRepository.save(drivingCity);
        } else {
            throw new NoSuchElementException();
        }
    }

    public DrivingCityDTO getDrivingCity(Long id) {
        Optional<DrivingCity> optionalDrivingCity = drivingCityRepository.findById(id);
        if (optionalDrivingCity.isPresent()) {
            return mapModelToDTO(optionalDrivingCity.get(), DrivingCityDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<DrivingCityDTO> getActiveCities() {
        return findActiveCities().stream().map(city->mapModelToDTO(city, DrivingCityDTO.builder().build()))
                .collect(Collectors.toList());
    }

    private Set<DrivingCity> findActiveCities() {
        return drivingCityRepository.findAllByActive(true);
    }

    public List<DrivingCityDTO> getAll() {
        return findAll().stream().map(city -> mapModelToDTO(city, DrivingCityDTO.builder().build()))
                .collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<DrivingCity>findByName(String name){
        return drivingCityRepository.findByName(name);
    }

    public List<DrivingCity> findAll() {
        return drivingCityRepository.findAll();
    }

    // -- mapper methods --
    public DrivingCityDTO mapModelToDTO(DrivingCity model, DrivingCityDTO dto) {
        return drivingCityMapper.mapModelToDTO(model, dto);
    }

    public DrivingCity mapDTOToModel(DrivingCityDTO dto, DrivingCity model) {
        if (dto.getId() != null) {
            Optional<DrivingCity> optionalDrivingCity = drivingCityRepository.findById(dto.getId());
            if (optionalDrivingCity.isPresent()) {
                model = optionalDrivingCity.get();
            }
        }
        model = drivingCityMapper.mapDTOToModel(dto, model);
        return drivingCityRepository.save(model);
    }
}
