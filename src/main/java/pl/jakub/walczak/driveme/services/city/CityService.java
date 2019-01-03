package pl.jakub.walczak.driveme.services.city;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.mappers.city.DrivingCityMapper;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Adding new DrivingCity...");
        Optional<DrivingCity> drivingCityOptional = drivingCityRepository.findByName(drivingCityDTO.getName());
        if (drivingCityOptional.isPresent()) {
            log.info("DrivingCity just exists in database, now is also activated");
            DrivingCity drivingCity = drivingCityOptional.get();
            drivingCity.setActive(true);
            return drivingCityRepository.save(drivingCity);
        }
        DrivingCity drivingCity = mapDTOToModel(drivingCityDTO, DrivingCity.builder().build());
        drivingCity.setActive(true);
        return drivingCityRepository.save(drivingCity);
    }

    public void deleteDrivingCity(Long id) {
        log.info("Deleting the DrivingCity with id = " + id);
        Optional<DrivingCity> drivingCityToDelete = drivingCityRepository.findById(id);
        if (drivingCityToDelete.isPresent()) {
            DrivingCity drivingCity = drivingCityToDelete.get();
            drivingCity.setActive(false);
            drivingCityRepository.save(drivingCity);
        } else {
            throw new NoSuchElementException("Cannot DELETE DrivingCity with given id = " + id);
        }
    }

    public DrivingCityDTO getDrivingCity(Long id) {
        log.info("Getting the DrivingCity with id = " + id);
        Optional<DrivingCity> optionalDrivingCity = drivingCityRepository.findById(id);
        return mapModelToDTO(optionalDrivingCity.orElseThrow(() ->
                new NoSuchElementException("Cannot GET DrivingCity with given id = " + id)), DrivingCityDTO.builder().build());
    }

    public List<DrivingCityDTO> getActiveCities() {
        log.info("Getting all active DrivingCities");
        return findActiveCities().stream().map(city -> mapModelToDTO(city, DrivingCityDTO.builder().build()))
                .collect(Collectors.toList());
    }

    public List<DrivingCityDTO> getAll() {
        log.info("Getting all DrivingCities");
        return findAll().stream().map(city -> mapModelToDTO(city, DrivingCityDTO.builder().build()))
                .collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<DrivingCity> findByName(String name) {
        return drivingCityRepository.findByName(name);
    }

    private Set<DrivingCity> findActiveCities() {
        return drivingCityRepository.findAllByActive(true);
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
            model = optionalDrivingCity.orElse(model);
        }
        model = drivingCityMapper.mapDTOToModel(dto, model);
        return drivingCityRepository.save(model);
    }
}
