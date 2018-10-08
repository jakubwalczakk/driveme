package pl.jakub.walczak.driveme.services.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.mappers.city.DrivingCityMapper;
import pl.jakub.walczak.driveme.model.city.DrivingCity;
import pl.jakub.walczak.driveme.repos.city.DrivingCityRepository;

import java.util.List;
import java.util.Optional;
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

    public DrivingCity save(DrivingCity drivingCity) {
        return drivingCityRepository.save(drivingCity);
    }

    public List<DrivingCity> findAll() {
        return drivingCityRepository.findAll();
    }

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

    public List<DrivingCityDTO> getAll() {
        return findAll().stream().map(city -> mapModelToDTO(city, DrivingCityDTO.builder().build())).collect(Collectors.toList());
    }
}
