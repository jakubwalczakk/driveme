package pl.jakub.walczak.driveme.mappers.city;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.model.city.DrivingCity;

@Component
public class DrivingCityMapper {

    public DrivingCityDTO mapModelToDTO(DrivingCity model, DrivingCityDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setActive(model.getActive());
        return dto;
    }

    public DrivingCity mapDTOToModel(DrivingCityDTO dto, DrivingCity model) {
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setActive(dto.getActive());
        return model;
    }
}
