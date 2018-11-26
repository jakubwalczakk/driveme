package pl.jakub.walczak.driveme.mappers.city;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.city.DrivingCityDTO;
import pl.jakub.walczak.driveme.model.city.DrivingCity;

import java.util.Base64;

@Component
public class DrivingCityMapper {

    public DrivingCityDTO mapModelToDTO(DrivingCity model, DrivingCityDTO dto) {
        dto.setId(model.getId());
        dto.setName(model.getName());
        try {
            dto.setImage(Base64.getEncoder().encodeToString(model.getImage()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setImage(null);
        }
//        dto.setImage(model.getImage());
        dto.setDescription(model.getDescription());
        dto.setActive(model.getActive());
        return dto;
    }

    public DrivingCity mapDTOToModel(DrivingCityDTO dto, DrivingCity model) {
        model.setId(dto.getId());
        model.setName(dto.getName());
        try {
            model.setImage(Base64.getDecoder().decode(dto.getImage()));
        } catch (Exception e) {
            e.printStackTrace();
            model.setImage(null);
        }
//        model.setImage(dto.getImage());
        model.setDescription(dto.getDescription());
        model.setActive(dto.getActive());
        return model;
    }
}
