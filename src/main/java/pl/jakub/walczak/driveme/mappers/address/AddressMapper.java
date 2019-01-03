package pl.jakub.walczak.driveme.mappers.address;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.address.AddressDTO;
import pl.jakub.walczak.driveme.model.address.Address;

@Component
public class AddressMapper {

    public AddressDTO mapModelToDTO(Address model, AddressDTO dto) {
        dto.setId(model.getId());
        dto.setCity(model.getCity());
        dto.setZipCode(model.getZipCode());
        dto.setStreet(model.getStreet());
        dto.setHouseNo(model.getHouseNo());
        return dto;
    }

    public Address mapDTOToModel(AddressDTO dto, Address model) {
        model.setId(dto.getId());
        model.setCity(dto.getCity() == null ? model.getCity() : dto.getCity());
        model.setZipCode(dto.getZipCode() == null ? model.getZipCode() : dto.getZipCode());
        model.setStreet(dto.getStreet() == null ? model.getStreet() : dto.getStreet());
        model.setHouseNo(dto.getHouseNo() == null ? model.getHouseNo() : dto.getHouseNo());
        return model;
    }
}
