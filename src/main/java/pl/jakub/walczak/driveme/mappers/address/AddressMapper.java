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
        model.setCity(dto.getCity());
        model.setZipCode(dto.getZipCode());
        model.setStreet(dto.getStreet());
        model.setHouseNo(dto.getHouseNo());
        return model;
    }
}
